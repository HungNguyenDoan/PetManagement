package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.JWT.JWTUserDetail;
import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.Cart;
import com.project.petmanagement.petmanagement.models.entity.CartItem;
import com.project.petmanagement.petmanagement.models.entity.Product;
import com.project.petmanagement.petmanagement.repositories.CartItemRepository;
import com.project.petmanagement.petmanagement.repositories.CartRepository;
import com.project.petmanagement.petmanagement.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public Cart getCartByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        return cartRepository.findByUser(jwtUserDetail.getUser());
    }

    @Transactional(rollbackFor = {Exception.class})
    public Cart addItemToCart(Long productId, Integer quantity) throws DataNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(() -> new DataNotFoundException("Can not find product with ID: " + productId));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        Cart cart = cartRepository.findByUser(jwtUserDetail.getUser());
        if (cart == null) {
            cart = Cart.builder()
                    .user(jwtUserDetail.getUser())
                    .build();
            cart = cartRepository.save(cart);
        }
        List<CartItem> cartItems = cart.getCartItems();
        CartItem existingCartItem = null;
        boolean existed = false;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getProduct().equals(product)) {
                existed = true;
                existingCartItem = cartItem;
                break;
            }
        }
        if (existed) {
            int newQuantity = existingCartItem.getQuantity() + quantity;
            existingCartItem.setQuantity(newQuantity);
            cartItemRepository.save(existingCartItem);
        } else {
            CartItem cartItem = CartItem.builder()
                    .cart(cart)
                    .product(product)
                    .quantity(quantity)
                    .build();
            cartItemRepository.save(cartItem);
        }
        cart = cartRepository.findByUser(jwtUserDetail.getUser());
        double totalPrice = 0;
        for (CartItem item : cart.getCartItems()) {
            totalPrice += item.getQuantity() * item.getProduct().getPrice();
        }
        cart.setTotalPrice(totalPrice);
        return cart;
    }

    @Transactional(rollbackFor = {Exception.class})
    public Cart updateItemInCart(Long itemId, Integer quantity) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        Cart cart = cartRepository.findByUser(jwtUserDetail.getUser());
        List<CartItem> cartItems = cart.getCartItems();
        if (quantity > 0) {
            for (CartItem cartItem : cartItems) {
                if (cartItem.getId().equals(itemId)) {
                    cartItem.setQuantity(quantity);
                    cartItemRepository.save(cartItem);
                    break;
                }
            }
        }
        cart = cartRepository.findByUser(jwtUserDetail.getUser());
        double totalPrice = 0;
        for (CartItem item : cart.getCartItems()) {
            totalPrice += item.getQuantity() * item.getProduct().getPrice();
        }
        cart.setTotalPrice(totalPrice);
        return cart;
    }

    @Transactional(rollbackFor = {Exception.class})
    public Cart deleteItemInCart(Long itemId) throws Exception {
        CartItem cartItem = cartItemRepository.findById(itemId).orElseThrow(() -> new DataNotFoundException("Can not find item with ID: " + itemId));
        if (cartItem != null) {
            cartItemRepository.deleteById(itemId);
        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        Cart cart = cartRepository.findByUser(jwtUserDetail.getUser());
        double totalPrice = 0;
        for (CartItem item : cart.getCartItems()) {
            totalPrice += item.getQuantity() * item.getProduct().getPrice();
        }
        cart.setTotalPrice(totalPrice);
        return cart;
    }
}