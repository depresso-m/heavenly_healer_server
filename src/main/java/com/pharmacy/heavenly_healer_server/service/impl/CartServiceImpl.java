package com.pharmacy.heavenly_healer_server.service.impl;

import com.pharmacy.heavenly_healer_server.dto.CartItemDto;
import com.pharmacy.heavenly_healer_server.model.Cart;
import com.pharmacy.heavenly_healer_server.model.Medication;
import com.pharmacy.heavenly_healer_server.model.User;
import com.pharmacy.heavenly_healer_server.repository.CartRepository;
import com.pharmacy.heavenly_healer_server.service.CartService;
import com.pharmacy.heavenly_healer_server.service.MedicationService;
import com.pharmacy.heavenly_healer_server.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final UserService userService;
    private final MedicationService medicationService;

    public CartServiceImpl(CartRepository cartRepository, UserService userService, MedicationService medicationService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.medicationService = medicationService;
    }

    @Override
    public List<CartItemDto> getUserCart(Integer userId) {
    List<Cart> list = cartRepository.findByUserId(userId);
        return list.stream()
                .map(cart -> new CartItemDto(
                        cart.getId(),
                        cart.getUser().getId(),
                        cart.getMedication().getId(),
                        cart.getQuantity()))
                .collect(Collectors.toList());
    }

    @Override
    public void addToCart(Integer userId, Integer medicationId, Integer quantity) {
        if (userId == null || medicationId == null || quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("User ID, medication ID, and quantity must be valid");
        }

        Cart cart = new Cart();

        User user = userService.getUserById(userId);
        Medication medication = medicationService.findMedicationEntityById(medicationId);

        cart.setUser(user);
        cart.setMedication(medication);
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    @Override
    public void updateCartItemQuantity(Integer userId, Integer cartItemId, Integer quantity) {
        Cart cart = cartRepository.findById(cartItemId).get();
        cart.setQuantity(quantity);
        cartRepository.save(cart);
    }

    @Transactional
    @Override
    public void removeCartItem(Integer cartItemId) {
        cartRepository.delete(cartRepository.findById(cartItemId).get());
    }

    @Transactional
    @Override
    public void clearUserCart(Integer userId) {
        cartRepository.deleteByUserId(userId);
    }
}
