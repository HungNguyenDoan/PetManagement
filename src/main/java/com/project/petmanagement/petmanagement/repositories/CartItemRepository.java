package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.Cart;
import com.project.petmanagement.petmanagement.models.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByIdAndCart(Long id, Cart cart);
}
