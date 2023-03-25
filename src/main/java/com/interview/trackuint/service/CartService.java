package com.interview.trackuint.service;

import com.interview.trackuint.dao.CartDao;
import com.interview.trackuint.domain.CartItem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartDao dao;

    public List<CartItem> getAllItems(String cartId) {
        return dao.load(cartId);
    }

    //needs transaction
    public List<CartItem> addItem(String cartId, CartItem cartItem) {
        List<CartItem> result = new LinkedList<>(dao.load(cartId));
        Optional<CartItem> alreadyInCartItem = result.stream()
                .filter(existing -> existing.id().equals(cartItem.id()))
                .findFirst();
        alreadyInCartItem.ifPresent(existing -> {
            result.remove(existing);
            result.add(new CartItem(cartItem.id(), existing.quantity() + cartItem.quantity()));
        });
        if (alreadyInCartItem.isEmpty()) {
            result.add(cartItem);
        }
        dao.save(cartId, result);
        return result;
    }

    //needs transaction
    public List<CartItem> removeItemById(String cartId, String itemId) {

        List<CartItem> result = dao.load(cartId).stream()
                .filter(cartItem -> !itemId.equals(cartItem.id()))
                .toList();
        dao.save(cartId, result);
        return result;
    }
}
