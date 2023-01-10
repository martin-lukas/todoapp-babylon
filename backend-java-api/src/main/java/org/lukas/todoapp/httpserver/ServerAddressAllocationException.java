package org.lukas.todoapp.httpserver;

public class ServerAddressAllocationException extends RuntimeException {
    public ServerAddressAllocationException(Throwable throwable) {
        super(throwable);
    }
}
