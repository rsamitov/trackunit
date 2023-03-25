package com.interview.trackuint.controller;

import com.interview.trackuint.domain.CartItem;
import com.interview.trackuint.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ShoppingCartController.class)
public class ShoppingCartControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CartService service;


    @Test
    void testList() throws Exception {
        when(service.getAllItems("1")).thenReturn(List.of(new CartItem("abc"), new CartItem("xyz")));

        mockMvc.perform(get("/cart/1/item"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"abc\"},{\"id\":\"xyz\"}]"));
    }

    @Test
    void testAdd() throws Exception {
        when(service.addItem("1", new CartItem("abc")))
                .thenReturn(List.of(new CartItem("abc"), new CartItem("xyz")));

        mockMvc.perform(post("/cart/1/item").contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":\"abc\", \"quantity\":1}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"abc\"},{\"id\":\"xyz\"}]"));
    }

    @Test
    void testDelete() throws Exception {
        when(service.removeItemById("1", "abc"))
                .thenReturn(List.of(new CartItem("xyz")));

        mockMvc.perform(delete("/cart/1/item/abc"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"id\":\"xyz\"}]"));
    }
}
