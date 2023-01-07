package org.lukas.todoapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange;
import org.lukas.todoapp.server.*;

import java.util.ArrayList;
import java.util.List;

public class TodoHandler implements CustomHandler {
    private final List<Todo> todos;

    public TodoHandler() {
        this.todos = new ArrayList<>();
    }

    @Override
    public Response doHandle(HttpExchange exchange) throws JsonProcessingException {
        return switch (HttpMethod.from(exchange.getRequestMethod())) {
            case GET -> getTodos();
            case POST -> addTodo(exchange);
            case DELETE -> deleteTodo(exchange);
        };
    }

    private Response getTodos() throws JsonProcessingException {
        return new Response(objectMapper.writeValueAsString(todos));
    }

    private Response addTodo(HttpExchange exchange) {
        // TODO: Implement adding a todo
        return new Response(HttpStatus.CREATED);
    }

    private Response deleteTodo(HttpExchange exchange) {
        // TODO: Implement deleting a todo
        return new Response();
    }
}
