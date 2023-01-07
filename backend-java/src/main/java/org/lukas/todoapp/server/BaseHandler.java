package org.lukas.todoapp.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class BaseHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Do nothing
        submitResponse(exchange);
    }

    protected void submitResponse(HttpExchange exchange) throws IOException {
        submitResponse(exchange, HttpStatus.OK);
    }

    protected void submitResponse(HttpExchange exchange, HttpStatus status)
            throws IOException {
        submitResponse(exchange, status, null);
    }

    protected void submitResponse(HttpExchange exchange, byte[] bytes)
            throws IOException {
        submitResponse(exchange, HttpStatus.OK, bytes);
    }

    protected void submitResponse(HttpExchange exchange, HttpStatus status, byte[] bytes)
            throws IOException {

        exchange.sendResponseHeaders(status.code(), bytes != null ? bytes.length : 0);

        if (bytes != null) {
            exchange.getResponseBody().write(bytes);
        }

        // Sends the HTTP response
        exchange.getResponseBody().close();
    }
}
