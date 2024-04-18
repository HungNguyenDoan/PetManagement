package com.project.petmanagement.petmanagement.services;

import com.project.petmanagement.petmanagement.JWT.JWTUserDetail;
import com.project.petmanagement.petmanagement.advices.DataNotFoundException;
import com.project.petmanagement.petmanagement.models.entity.*;
import com.project.petmanagement.petmanagement.models.enums.OrderStatusEnum;
import com.project.petmanagement.petmanagement.payloads.requests.OrderRequest;
import com.project.petmanagement.petmanagement.repositories.CartItemRepository;
import com.project.petmanagement.petmanagement.repositories.CartRepository;
import com.project.petmanagement.petmanagement.repositories.OrderDetailRepository;
import com.project.petmanagement.petmanagement.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    public List<Order> getOrderByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        User user = jwtUserDetail.getUser();
        return orderRepository.findByUser(user);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Order createOrder(OrderRequest orderRequest) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        Cart cart = cartRepository.findByUser(jwtUserDetail.getUser());
        if (cart == null || cart.getCartItems() == null) {
            throw new DataNotFoundException("Cart is empty");
        }
        List<CartItem> cartItems = cart.getCartItems();
        Order order = new Order();
        order.setStatus(OrderStatusEnum.PENDING);
        order.setPaymentMethod(orderRequest.getPaymentMethod());
        order.setShippingAddress(orderRequest.getShippingAddress());
        order.setTotalPrice(cart.getTotalPrice());
        order.setPhone(orderRequest.getPhone());
        order.setUser(cart.getUser());
        order.setOrderDate(new Date());
        order = orderRepository.save(order);
        for (CartItem cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setPrice(cartItem.getProduct().getPrice());
            orderDetail.setOrder(order);
            orderDetailRepository.save(orderDetail);
            cartItemRepository.delete(cartItem);
        }
        cartRepository.delete(cart);
        return order;
    }
}