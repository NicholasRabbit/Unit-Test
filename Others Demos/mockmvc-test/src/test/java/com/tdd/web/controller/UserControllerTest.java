package com.tdd.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

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
        MvcResult mvcResult = mockMvc.perform(get("/user/getByName")
                        .param("name", "Tom"))
                .andExpect(status().isOk())
                .andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        String jsonString = response.getContentAsString();
        JSONObject jsonObj = new JSONObject(jsonString);

        JSONObject entries = JSONUtil.parseObj(jsonObj.get("data"));
        assertEquals(entries.get("bucketName"), "e-task");
        assertEquals(entries.get("fileName"), "WX_GQ_0000000757-2024-1003.pdf");
        assertEquals(entries.get("url"), String.format("/admin/sys-file/%s/%s", "e-task", "WX_GQ_0000000757-2024-1003.pdf"));

    }

}