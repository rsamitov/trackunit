package com.interview.trackuint.dao.impl;

import com.interview.trackuint.dao.CartDao;
import com.interview.trackuint.domain.CartItem;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapBackedDao implements CartDao {

    private final Map<String, List<CartItem>> storage =
            Collections.synchronizedMap(new HashMap<>());

    @Override
    public List<CartItem> load(String cartId) {
        return storage.getOrDefault(cartId, Collections.emptyList());
    }

    @Override
    public void save(String cartId, List<CartItem> items) {
        storage.put(cartId, items);
    }
}
