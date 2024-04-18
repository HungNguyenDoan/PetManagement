package com.project.petmanagement.petmanagement.repositories;

import com.project.petmanagement.petmanagement.models.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select p from products p " +
            "left join categories_products cp on p.id = cp.product_id " +
            "left join categories c on c.id = cp.category_id " +
            "where c.id = :categoryId", nativeQuery = true)
    List<Product> findByCategory(@Param("categoryId") Long categoryId);

    List<Product> findByNameContainingIgnoreCase(String name);
    List<Product> findByIsActiveTrue();
}
