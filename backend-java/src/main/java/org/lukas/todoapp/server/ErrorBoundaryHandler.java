package org.lukas.todoapp.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class ErrorBoundaryHandler implements HttpHandler {
    private final CustomHandler customHandler;

    public ErrorBoundaryHandler(CustomHandler customHandler) {
        this.customHandler = customHandler;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            Response response = customHandler.doHandle(exchange);
            submitResponse(exchange, response.status(), response.body().getBytes());
        } catch (Throwable ex) {
            submitResponse(
                    exchange,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "There was an exception thrown: %s".formatted(ex.getMessage()).getBytes()
            );
        }
    }

    protected void submitResponse(HttpExchange exchange, HttpStatus status, byte[] bytes)
            throws IOException {

        exchange.sendResponseHeaders(
                status != null ? status.code() : HttpStatus.OK.code(),
                bytes != null ? bytes.length : 0
        );

        if (bytes != null) {
            exchange.getResponseBody().write(bytes);
        }

        // Sends the HTTP response
        exchange.getResponseBody().close();
    }
}
