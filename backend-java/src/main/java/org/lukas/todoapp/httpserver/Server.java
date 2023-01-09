package org.lukas.todoapp.httpserver;

import com.sun.net.httpserver.HttpServer; //NOSONAR

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    private final HttpServer httpServer;

    public Server(int port) {
        try {
            this.httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        } catch (IOException ex) {
            throw new ServerAddressAllocationException(ex);
        }
    }

    public void createContext(String path, CustomHandler handler) {
        httpServer.createContext(path + '/', new ErrorBoundaryHandler(handler));
    }

    public void start() {
        httpServer.start();
    }

    public void stop() {
        httpServer.stop(0);
    }
}
