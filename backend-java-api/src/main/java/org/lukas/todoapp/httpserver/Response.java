package org.lukas.todoapp.httpserver;

public record Response(HttpStatus status, String body) {
    public Response(String body) {
        this(null, body);
    }

    public Response() {
        this(null, null);
    }
}
