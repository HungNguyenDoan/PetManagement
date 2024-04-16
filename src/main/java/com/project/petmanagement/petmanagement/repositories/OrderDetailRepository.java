package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.Order;
import com.project.petmanagement.petmanagement.models.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findByOrder(Order order);
}
