package com.tdd.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(new UserController())
                .defaultRequest(get("/").accept(MediaType.APPLICATION_JSON))
                .alwaysExpect(status().isOk())
                .alwaysExpect(content().contentType("application/json;charset=UTF-8"))
                .build();
    }


    @Test
    public void testGetWithMockMvc() throws Exception {
        mockMvc.perform(get("/user/1001"));
    }

    @Test
    public void getByName() throws Exception {
        mockMvc.perform(get("/user/getByName")
                .param("name", "Tom"));
    }

}