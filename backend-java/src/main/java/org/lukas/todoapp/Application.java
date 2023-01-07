package org.lukas.todoapp;

import org.lukas.todoapp.server.Server;

public class Application {
    public static void main(String[] args) {
        Server server = new Server(8080);
        server.createContext("/api/todos", new TodoHandler());
        server.start();
    }
}
