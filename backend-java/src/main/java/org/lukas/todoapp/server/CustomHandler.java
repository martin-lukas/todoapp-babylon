package org.lukas.todoapp.server;

import com.sun.net.httpserver.HttpExchange;

public interface CustomHandler {
    Response doHandle(HttpExchange exchange) throws Exception;
}
