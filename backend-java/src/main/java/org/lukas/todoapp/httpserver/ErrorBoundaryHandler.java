package org.lukas.todoapp.httpserver;

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
            submitResponse(exchange, response.status(), response.body());
        } catch (Throwable ex) {
            submitResponse(
                    exchange,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "There was an exception thrown: %s".formatted(ex.getMessage())
            );
        }
    }

    protected void submitResponse(HttpExchange exchange, HttpStatus status, String responseBody)
            throws IOException {

        exchange.sendResponseHeaders(
                status != null ? status.code() : HttpStatus.OK.code(),
                responseBody != null ? responseBody.getBytes().length : 0
        );

        if (responseBody != null) {
            exchange.getResponseBody().write(responseBody.getBytes());
        }

        // Sends the HTTP response
        exchange.getResponseBody().close();
    }
}
