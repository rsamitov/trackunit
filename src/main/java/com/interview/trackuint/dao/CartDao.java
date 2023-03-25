package com.interview.trackuint.dao;

import com.interview.trackuint.domain.CartItem;

import java.util.List;

//adding interface because my impl is a dummy
public interface CartDao {

    List<CartItem> load(String cartId);

    void save(String cartId, List<CartItem> items);
}
