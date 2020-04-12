package com.khaago.lethe;

import com.khaago.lethe.config.ServerManager;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Lethe implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Lethe.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        if(args == null || args.length == 0) args = new String[]{"50051"};
        ServerManager serverManager = new ServerManager();
        serverManager.start(Integer.parseInt(args[0]));
        serverManager.blockUntilShutdown();
    }
}
