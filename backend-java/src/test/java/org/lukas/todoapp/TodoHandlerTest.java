package org.lukas.todoapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TodoHandlerTest {
    private static final String API_URL = "http://localhost:8080/api/todos/";

    private static HttpClient httpClient;
    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void setUp() {
        Application.main(new String[] {});
        objectMapper = new ObjectMapper();
        httpClient = HttpClient.newHttpClient();
    }

    @Test
    void callGetTodos() throws IOException, InterruptedException {
        HttpResponse<String> response = fetchGetTodosAsString();

        assertEquals(
                objectMapper.writeValueAsString(new ArrayList<Todo>()),
                response.body()
        );
    }

    @Test
    void callAddTodoAndGetTodos() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest addTodoRequest = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();
        HttpResponse<String> response = client
                .send(addTodoRequest, HttpResponse.BodyHandlers.ofString());

        assertEquals(
                objectMapper.writeValueAsString(new ArrayList<Todo>()),
                response.body()
        );
    }

    private HttpResponse<String> fetchGetTodosAsString() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        return httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());
    }
}
