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

    public List<Order> getOrdersByUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        User user = jwtUserDetail.getUser();
        return orderRepository.findByUser(user);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Order createOrder(OrderRequest orderRequest) throws DataNotFoundException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        JWTUserDetail jwtUserDetail = (JWTUserDetail) authentication.getPrincipal();
        Cart cart = cartRepository.findByUser(jwtUserDetail.getUser());
        boolean existedSelectedItem = false;
        for (CartItem item : cart.getCartItems()) {
            if (item.getSelected()) {
                existedSelectedItem = true;
                break;
            }
        }
        if (cart.getCartItems().isEmpty()) {
            throw new DataNotFoundException("Cart is empty");
        }
        if (!existedSelectedItem) {
            throw new DataNotFoundException("No item to create order");
        }
        Order order = Order.builder()
                .user(cart.getUser())
                .orderDate(new Date())
                .phone(orderRequest.getPhone())
                .shippingAddress(orderRequest.getShippingAddress())
                .paymentMethod(orderRequest.getPaymentMethod())
                .status(OrderStatusEnum.PENDING)
                .build();
        order = orderRepository.save(order);
        List<CartItem> cartItems = cart.getCartItems();
        double totalPrice = 0;
        for (CartItem cartItem : cartItems) {
            if (cartItem.getSelected()) {
                OrderDetail orderDetail = OrderDetail.builder()
                        .order(order)
                        .product(cartItem.getProduct())
                        .quantity(cartItem.getQuantity())
                        .price(cartItem.getProduct().getPrice())
                        .build();
                orderDetailRepository.save(orderDetail);
                cartItemRepository.deleteById(cartItem.getId());
                totalPrice += orderDetail.getPrice() * orderDetail.getQuantity();
            }
        }
        order.setTotalPrice(totalPrice);
        return orderRepository.save(order);
    }

    @Transactional(rollbackFor = {Exception.class})
    public Order cancelOrder(Long orderId) throws Exception {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new DataNotFoundException("Can not found order with ID: " + orderId));
        order.setStatus(OrderStatusEnum.CANCELLED);
        return orderRepository.save(order);
    }
}