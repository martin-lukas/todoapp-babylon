package org.lukas.todoapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TodoHandlerTest {
    private static final String API_URL = "http://localhost:8080/api/todos/";

    private static HttpClient httpClient;
    private static ObjectMapper objectMapper;

    @BeforeAll
    public static void initialize() {
        objectMapper = new ObjectMapper();
        httpClient = HttpClient.newHttpClient();
    }

    @BeforeEach
    public void setUp() {
        Application.main(new String[] {});
    }

    @AfterEach
    public void tearDown() {
        Application.stopServer();
    }

    @Test
    void callGetTodos_thenReceiveEmptyList() throws IOException, InterruptedException {
        assertEquals(
                objectMapper.writeValueAsString(new ArrayList<Todo>()),
                callGetTodos().body()
        );
    }

    @Test
    void callAddTodo_thenGetTodosReturnsListOfOne() throws IOException, InterruptedException {
        assertEquals(201, callAddTodo(new Todo("My first todo")).statusCode());

        HttpResponse<String> getTodosResponse = callGetTodos();
        List<Todo> todos = objectMapper.readValue(
                getTodosResponse.body(),
                new TypeReference<>() {}
        );

        assertEquals(1, todos.size());
        assertEquals("My first todo", todos.get(0).content());
    }

    private HttpResponse<String> callGetTodos() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .GET()
                .build();

        return httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());
    }

    @Test
    void callAddTodoAndDeleteTodo_thenGetTodosReturnsEmpty()
            throws IOException, InterruptedException {

        assertEquals(201, callAddTodo(new Todo("My first todo")).statusCode());

        HttpResponse<String> getTodosResponse = callGetTodos();
        List<Todo> todos = objectMapper.readValue(
                getTodosResponse.body(),
                new TypeReference<>() {}
        );

        assertEquals(1, todos.size());
        assertEquals(200, callDeleteTodo(todos.get(0)).statusCode());

    }

    private HttpResponse<Void> callDeleteTodo(Todo todo) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .method(
                        "DELETE",
                        HttpRequest.BodyPublishers.ofString(
                                objectMapper.writeValueAsString(todo)
                        )
                )
                .build();

        return httpClient
                .send(request, HttpResponse.BodyHandlers.discarding());
    }

    private HttpResponse<Void> callAddTodo(Todo todo) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .POST(HttpRequest.BodyPublishers.ofString(
                        objectMapper.writeValueAsString(todo)
                ))
                .build();

        return httpClient
                .send(request, HttpResponse.BodyHandlers.discarding());
    }
}
