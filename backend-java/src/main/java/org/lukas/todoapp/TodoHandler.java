package org.lukas.todoapp;

import com.sun.net.httpserver.HttpExchange;
import org.lukas.todoapp.server.*;

public class TodoHandler implements CustomHandler {
    @Override
    public Response doHandle(HttpExchange exchange) {
        return switch (HttpMethod.from(exchange.getRequestMethod())) {
            case GET -> getTodos();
            case POST -> addTodo(exchange);
            case DELETE -> deleteTodo(exchange);
        };
    }

    private Response getTodos() {
        return new Response("hello");
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
