package com.interview.trackuint.domain;

import jakarta.validation.constraints.NotBlank;

public record CartItem(@NotBlank String id, int quantity) {
    public CartItem(@NotBlank String id) {
        this(id, 1);
    }
}
