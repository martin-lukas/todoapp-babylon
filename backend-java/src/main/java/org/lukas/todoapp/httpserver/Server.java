package org.lukas.todoapp.httpserver;

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

    public void createContext(String path, CustomHandler handler) {
        httpServer.createContext(path + '/', new ErrorBoundaryHandler(handler));
    }

    public void start() {
        httpServer.start();
    }
}
