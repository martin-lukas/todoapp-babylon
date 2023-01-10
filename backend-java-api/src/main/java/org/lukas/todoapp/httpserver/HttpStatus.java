package org.lukas.todoapp.httpserver;

public enum HttpStatus {
    OK(200),
    CREATED(201),
    INTERNAL_SERVER_ERROR(500);

    private final int statusCode;

    HttpStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int code() {
        return statusCode;
    }
}
