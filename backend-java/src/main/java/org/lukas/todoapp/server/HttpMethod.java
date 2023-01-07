package org.lukas.todoapp.server;

import java.util.Arrays;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    DELETE("DELETE");

    private final String method;

    HttpMethod(String method) {
        this.method = method;
    }

    public static HttpMethod from(String requestMethod) {
        return Arrays.stream(values())
                .filter(method -> method.method().equals(requestMethod))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "The HTTP method '%s' doesn't exist".formatted(requestMethod)
                        )
                );
    }

    public String method() {
        return method;
    }
}
