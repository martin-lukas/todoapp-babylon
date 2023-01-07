package org.lukas.todoapp.server;

public record Response(HttpStatus status, String body) {
    public Response(HttpStatus status) {
        this(status, null);
    }

    public Response(String body) {
        this(null, body);
    }

    public Response() {
        this(null, null);
    }
}
