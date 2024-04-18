package com.project.petmanagement.petmanagement.models.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.petmanagement.petmanagement.models.enums.ProductStatusEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Entity
@Table(name = "products")
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "image", length = 30000)
    private String image;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProductStatusEnum status;

    @Column(name = "description", length = 30000)
    private String description;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    @JsonBackReference
    private Category category;
}
