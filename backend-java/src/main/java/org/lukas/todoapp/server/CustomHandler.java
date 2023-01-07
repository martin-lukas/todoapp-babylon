package org.lukas.todoapp.server;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;

public interface CustomHandler {
    ObjectMapper objectMapper = new ObjectMapper();

    Response doHandle(HttpExchange exchange) throws Exception;
}
