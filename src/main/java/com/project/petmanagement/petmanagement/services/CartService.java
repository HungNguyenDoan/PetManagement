package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.JWT.JWTUserDetail;
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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public Cart getCart(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        return cartRepository.findByUser(jwtUserDetail.getUser());
    }
    @Transactional
    public Cart addToCart(long idProduct, int quantity){
        Product product = productRepository.findById(idProduct).orElse(null);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        Cart cart = cartRepository.findByUser(jwtUserDetail.getUser());
        if(cart == null){
            cart = new Cart();
            cart.setUser(jwtUserDetail.getUser());
            cart = cartRepository.save(cart);
        }
        List<CartItem> cartItems = new ArrayList<>();
        if(cart.getCartItems()!=null){
            cartItems.addAll(cart.getCartItems());
        }
        if (cartItems.isEmpty()){
            CartItem cartItem = new CartItem();
            cartItem.setCart(cart);
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItems.add(cartItem);
            cartItemRepository.save(cartItem);
        }else {
            CartItem cartItem = null;
            for (CartItem cartItem1: cartItems){
                if(cartItem1.getProduct().getId() == idProduct){
                    cartItem = cartItem1;
                    break;
                }
            }
            if(cartItem == null){
                CartItem cartItem1 = new CartItem();
                cartItem1.setCart(cart);
                cartItem1.setProduct(product);
                cartItem1.setQuantity(quantity);
                cartItems.add(cartItem1);
                cartItemRepository.save(cartItem1);
            }else{
                cartItem.setQuantity(cartItem.getQuantity()+quantity);
                cartItemRepository.save(cartItem);
            }
        }
        double totalPrice=0;
        for(CartItem item:cartItems){
            totalPrice += item.getQuantity()*item.getProduct().getPrice();
        }
        cart.setCartItems(cartItems);
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }

    @Transactional
    public Cart updateCart(long idItem, int quantity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        Cart cart = cartRepository.findByUser(jwtUserDetail.getUser());
        List<CartItem> cartItems = cart.getCartItems();
        if(quantity > 0){
            for(CartItem cartItem: cartItems){
                if(cartItem.getId() == idItem){
                    cartItem.setQuantity(quantity);
                    cartItemRepository.save(cartItem);
                    break;
                }
            }
        }else {
            CartItem cartItem = cartItemRepository.findById(idItem).orElse(null);
            if (cartItem != null) {
                cartItems.remove(cartItem);
                cartItemRepository.delete(cartItem);
            }
        }
        double totalPrice=0;
        for(CartItem item:cartItems){
            totalPrice += item.getQuantity()*item.getProduct().getPrice();
        }
        cart.setCartItems(cartItems);
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }
    public Cart deleteItem(long idItem){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        Cart cart = cartRepository.findByUser(jwtUserDetail.getUser());
        List<CartItem> cartItems = cart.getCartItems();
        CartItem cartItem = cartItemRepository.findById(idItem).orElse(null);
        if (cartItem != null) {
            cartItems.remove(cartItem);
            cartItemRepository.delete(cartItem);
        }
        double totalPrice=0;
        for(CartItem item:cartItems){
            totalPrice += item.getQuantity()*item.getProduct().getPrice();
        }
        cart.setCartItems(cartItems);
        cart.setTotalPrice(totalPrice);
        return cartRepository.save(cart);
    }
}