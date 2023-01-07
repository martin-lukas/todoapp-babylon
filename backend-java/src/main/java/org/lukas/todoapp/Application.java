package org.lukas.todoapp;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

public class Application {
    private static final int PORT = 8080;
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = HttpServer.create(
                new InetSocketAddress(InetAddress.getLoopbackAddress(), PORT),
                0
        );

        System.out.println("HTTP server is starting on port " + PORT);
        httpServer.start();
    }
}
