package com.pharmacy.heavenly_healer_server.controller;

import com.pharmacy.heavenly_healer_server.dto.CartItemDto;
import com.pharmacy.heavenly_healer_server.service.CartService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Контроллер управления корзиной

//todo переписать после добавления пользователя

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService)
    {
        this.cartService = cartService;
    }

    @GetMapping("/{userId}")
    public List<CartItemDto> getUserCart(@PathVariable Integer userId)
    {
        return cartService.getUserCart(userId);
    }

    @PostMapping("/{userId}/items")
    public void addToCart(@PathVariable Integer userId,
                          @RequestParam Integer medicationId,
                          @RequestParam Integer quantity)
    {
        cartService.addToCart(userId, medicationId, quantity);
    }

    @PutMapping("/{userId}/items/{cartItemId}")
    public void updateCartItemQuantity(@PathVariable Integer userId,
                                       @PathVariable Integer cartItemId,
                                       @RequestParam Integer quantity)
    {
        cartService.updateCartItemQuantity(userId, cartItemId, quantity);
    }

    @DeleteMapping("/items/{cartItemId}")
    public void removeCartItem(@PathVariable Integer cartItemId)
    {
        cartService.removeCartItem(cartItemId);
    }

    @DeleteMapping("/{userId}")
    public void clearUserCart(@PathVariable Integer userId)
    {
        cartService.clearUserCart(userId);
    }
}
