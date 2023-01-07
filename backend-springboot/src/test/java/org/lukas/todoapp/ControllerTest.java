package org.lukas.todoapp;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = Controller.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ControllerTest {
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void whenGettingTodosWithoutAdding_thenReturnEmptyList() throws Exception {
        callGetTodosEndpoint()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void whenAddingTodo_thenReturns201() throws Exception {
        callAddTodoEndpoint(new Todo(null, "First todo"))
                .andExpect(status().isCreated());
    }

    @Test
    void whenAddingTodo_thenGetTodosReturnsOneTodo() throws Exception {
        callAddTodoEndpoint(new Todo(null, "First todo"));
        callGetTodosEndpoint()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].id").isString())
                .andExpect(jsonPath("$[0].content").value("First todo"));
    }

    @Test
    void whenDeletingLastTodo_thenReturnsEmptyList() throws Exception {
        callAddTodoEndpoint(new Todo(null, "First todo"));
        String todoListJson = callGetTodosEndpoint()
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Todo> todos = objectMapper.readValue(todoListJson, new TypeReference<>() {});

        assertEquals(1, todos.size());
        assertNotNull(todos.get(0).id());

        callDeleteTodoEndpoint(todos.get(0))
                .andExpect(status().isOk());

        callGetTodosEndpoint()
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    private ResultActions callGetTodosEndpoint() throws Exception {
        return mockMvc.perform(get("/api/todos")
                .contentType("application/json")
        );
    }

    private ResultActions callAddTodoEndpoint(Todo todo) throws Exception {
        return mockMvc.perform(post("/api/todos")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(todo))
        );
    }

    private ResultActions callDeleteTodoEndpoint(Todo todo) throws Exception {
        return mockMvc.perform(delete("/api/todos")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(todo))
        );
    }
}