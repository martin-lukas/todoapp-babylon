package org.lukas.todoapp;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class TodoHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (exchange.getRequestMethod()) {
            case "GET" -> handleGet(exchange);
            case "POST" -> handlePost(exchange);
            case "DELETE" -> handleDelete(exchange);
            default -> submitResponse(exchange, null, 404);
        }
    }

    private void handleGet(HttpExchange exchange) throws IOException {
        submitResponse(exchange, "hello".getBytes(), 200);
    }

    private void handlePost(HttpExchange exchange) throws IOException {
        submitResponse(exchange, null, 201);
    }

    private void handleDelete(HttpExchange exchange) throws IOException {
        submitResponse(exchange, null, 200);
    }

    private void submitResponse(HttpExchange exchange, byte[] bytes, int status)
            throws IOException {

        exchange.sendResponseHeaders(status, bytes != null ? bytes.length : 0);

        if (bytes != null) {
            exchange.getResponseBody().write(bytes);
        }

        // Sends the HTTP response
        exchange.getResponseBody().close();
    }
}
