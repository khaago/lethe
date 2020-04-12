package com.khaago.lethe.config;

import com.khaago.lethe.grpc.ClientService;
import com.khaago.lethe.grpc.EventService;
import com.khaago.lethe.grpc.TopicService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class ServerManager {

    private Server server;
    private static final Logger logger = LoggerFactory.getLogger(ServerManager.class);

    @Autowired
    ClientService clientService;

    @Autowired
    TopicService topicService;

    @Autowired
    EventService eventService;

    public void start(int port) throws IOException {
        /* The port on which the server should run */
        server = ServerBuilder.forPort(port)
                .addService(topicService)
                .addService(eventService)
                .addService(clientService)
                .build()
                .start();
        logger.debug("Server started, listening on {}", port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            try {
                ServerManager.this.stop();
            } catch (InterruptedException e) {
                System.err.printf("InterruptedException %s", e.getMessage());
                Thread.currentThread().interrupt();
            }
            System.err.println("*** server shut down");
        }));
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
