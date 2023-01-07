package org.lukas.todoapp;

import com.sun.net.httpserver.HttpExchange;
import org.lukas.todoapp.server.BaseHandler;
import org.lukas.todoapp.server.HttpMethod;
import org.lukas.todoapp.server.HttpStatus;

import java.io.IOException;

public class TodoHandler extends BaseHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        switch (HttpMethod.from(exchange.getRequestMethod())) {
            case GET -> getTodos(exchange);
            case POST -> addTodo(exchange);
            case DELETE -> deleteTodo(exchange);
            default -> submitResponse(exchange, HttpStatus.NOT_FOUND, null);
        }
    }

    private void getTodos(HttpExchange exchange) throws IOException {
        submitResponse(exchange, "hello".getBytes());
    }

    private void addTodo(HttpExchange exchange) throws IOException {
        // TODO: Implement adding a todo
        submitResponse(exchange, HttpStatus.CREATED);
    }

    private void deleteTodo(HttpExchange exchange) throws IOException {
        // TODO: Implement deleting a todo
        submitResponse(exchange);
    }
}
