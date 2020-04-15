package com.khaago.lethe;

import com.khaago.lethe.config.ServerManager;
import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
@EnableIgniteRepositories
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
        serverManager.start(grpcPort);
        serverManager.blockUntilShutdown();
    }
}
