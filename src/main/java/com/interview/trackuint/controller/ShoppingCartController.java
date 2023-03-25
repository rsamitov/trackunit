package com.interview.trackuint.controller;

import com.interview.trackuint.domain.CartItem;
import com.interview.trackuint.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final CartService cartService;

    @GetMapping("/{cartId}/item")
    public List<CartItem> list(@PathVariable String cartId) {
        return cartService.getAllItems(cartId);
    }

    @PostMapping("/{cartId}/item")
    public List<CartItem> add(@PathVariable String cartId,
                              @Valid @RequestBody CartItem cartItem) {
        return cartService.addItem(cartId, cartItem);
    }

    @DeleteMapping("/{cartId}/item/{itemId}")
    public List<CartItem> remove(@PathVariable String cartId, @PathVariable String itemId) {
        return cartService.removeItemById(cartId, itemId);
    }
}
