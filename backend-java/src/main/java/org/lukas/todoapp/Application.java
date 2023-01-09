package org.lukas.todoapp;

import org.lukas.todoapp.httpserver.Server;

public class Application {
    private static Server server;

    public static void main(String[] args) {
        server = new Server(8080);
        server.createContext("/api/todos", new TodoHandler());
        server.start();
    }

    public static void stopServer() {
        server.stop();
    }
}
