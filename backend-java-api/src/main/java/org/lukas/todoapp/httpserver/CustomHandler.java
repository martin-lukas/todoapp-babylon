package org.lukas.todoapp.httpserver;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange; //NOSONAR

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public interface CustomHandler {
    ObjectMapper objectMapper = new ObjectMapper();

    @SuppressWarnings("java:S112")
    Response doHandle(HttpExchange exchange) throws Exception;

    default String serialize(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    default <T> T deserialize(InputStream inputStream, Class<T> targetClass)
            throws IOException {

        String serializedString = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        return objectMapper.readValue(serializedString, targetClass);
    }
}
