package org.lukas.todoapp;

import java.util.UUID;

public record Todo(UUID id, String content) {
    public Todo(String content) {
        this(null, content);
    }
}
