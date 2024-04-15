package com.project.petmanagement.petmanagement.models.entity;

import com.project.petmanagement.petmanagement.models.enums.ProductStatusEnum;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "image", length = 30000, nullable = false)
    private String image;

    @Column(name = "status", nullable = false)
    private ProductStatusEnum status;

    @Column(name = "description")
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    // Products quan hệ n - n với Category
    @ManyToMany(mappedBy = "products")
    private List<Category> categories;
}
