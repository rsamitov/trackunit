package com.interview.trackuint.service;

import com.interview.trackuint.dao.CartDao;
import com.interview.trackuint.domain.CartItem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CartServiceTest {

    @MockBean
    CartDao dao;

    @Autowired
    CartService cartService;

    @Test
    void testGetAllItems() {
        List<CartItem> items = List.of(new CartItem("abc"), new CartItem("xyz"));
        when(dao.load("1")).thenReturn(items);
        assertThat(cartService.getAllItems("1"))
                .containsAll(items);
    }

    @Test
    void testAddItem() {
        CartItem abc = new CartItem("abc");
        CartItem xyz = new CartItem("xyz");
        List<CartItem> items = List.of(abc, xyz);
        when(dao.load("1")).thenReturn(List.of(abc));
        assertThat(cartService.addItem("1", xyz)).containsAll(items);
        verify(dao).save("1", items);
    }

    @Test
    void testAddItem_addSecond() {
        CartItem abc = new CartItem("abc");
        CartItem xyz = new CartItem("xyz");
        when(dao.load("1")).thenReturn(List.of(abc, xyz));
        List<CartItem> items = List.of(abc, new CartItem(xyz.id(), 2));
        assertThat(cartService.addItem("1", xyz)).containsAll(items);
        verify(dao).save("1", items);
    }

    @Test
    void testRemoveItem() {
        CartItem abc = new CartItem("abc");
        CartItem xyz = new CartItem("xyz");
        when(dao.load("1")).thenReturn(List.of(abc, xyz));
        assertThat(cartService.removeItemById("1", xyz.id())).containsAll(List.of(abc));
        verify(dao).save("1", List.of(abc));
    }
}
