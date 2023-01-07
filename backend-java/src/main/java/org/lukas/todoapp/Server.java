package org.lukas.todoapp;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private final HttpServer httpServer;

    public Server(int port) {
        try {
            this.httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createContext(String path, HttpHandler handler) {
        httpServer.createContext(path + '/', handler);
    }

    public void start() {
        httpServer.start();
    }
}
