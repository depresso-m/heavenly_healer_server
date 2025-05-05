package com.pharmacy.heavenly_healer_server.service;

import com.pharmacy.heavenly_healer_server.dto.CartItemDto;

import java.util.List;

public interface CartService {
    List<CartItemDto> getUserCart(Integer userId);

    void addToCart(Integer userId, Integer medicationId, Integer quantity);

    void updateCartItemQuantity(Integer userId, Integer cartItemId, Integer quantity);

    void removeCartItem(Integer cartItemId);

    void clearUserCart(Integer userId);
}
