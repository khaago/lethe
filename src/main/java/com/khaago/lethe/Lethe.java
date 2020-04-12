package com.khaago.lethe;

import com.khaago.lethe.config.ServerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Lethe implements CommandLineRunner {

    @Autowired
    ServerManager serverManager;

    @Value("${grpc.port}")
    Integer grpcPort;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Lethe.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        int port = (args == null || args.length == 0) ? grpcPort : Integer.parseInt(args[0]);
        serverManager.start(port);
        serverManager.blockUntilShutdown();
    }
}
