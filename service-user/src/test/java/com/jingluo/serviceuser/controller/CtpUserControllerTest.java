package com.jingluo.serviceuser.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CtpUserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void whetherToBanTest() throws Exception {
        mockMvc.perform(post("/user/ban-status"))
                .andExpect(status().isOk())
                .andExpect(content().string("11235513431;2026-11-18T07:16:32.899980500Z"));
    }

}
