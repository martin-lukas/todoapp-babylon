package org.lukas.todoapp;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/todos")
public class Controller {
    private final List<Todo> todos = new ArrayList<>();

    @GetMapping
    public List<Todo> getTodos() {
        return todos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTodo(@RequestBody Todo todo) {
        Todo todoWithId = new Todo(UUID.randomUUID(), todo.content());
        todos.add(todoWithId);
    }

    @DeleteMapping
    public void deleteTodo(@RequestBody Todo todo) {
        todos.remove(todo);
    }
}
