package com.khaago.lethe;

import com.khaago.lethe.config.ServerManager;

import java.io.IOException;

public class Lethe {

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerManager serverManager = new ServerManager();
        serverManager.start(Integer.parseInt(args[0]));
        serverManager.blockUntilShutdown();
    }
}
