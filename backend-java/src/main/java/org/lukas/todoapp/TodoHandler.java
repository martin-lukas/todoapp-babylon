package org.lukas.todoapp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange; //NOSONAR
import org.lukas.todoapp.httpserver.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TodoHandler implements CustomHandler {
    private final List<Todo> todos;

    public TodoHandler() {
        this.todos = new ArrayList<>();
    }

    @Override
    public Response doHandle(HttpExchange exchange) throws IOException {
        return switch (HttpMethod.from(exchange.getRequestMethod())) {
            case GET -> getTodos();
            case POST -> addTodo(exchange);
            case DELETE -> deleteTodo(exchange);
        };
    }

    private Response getTodos() throws JsonProcessingException {
        return new Response(serialize(todos));
    }

    private Response addTodo(HttpExchange exchange) throws IOException {
        Todo todo = deserialize(exchange.getRequestBody(), Todo.class);
        Todo newTodo = new Todo(UUID.randomUUID(), todo.content());
        todos.add(newTodo);
        return new Response(HttpStatus.CREATED, serialize(newTodo));
    }

    private Response deleteTodo(HttpExchange exchange) throws IOException {
        Todo todo = deserialize(exchange.getRequestBody(), Todo.class);
        todos.remove(todo);
        return new Response();
    }
}
