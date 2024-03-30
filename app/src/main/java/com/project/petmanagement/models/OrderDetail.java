package com.project.petmanagement.models;

public class OrderDetail {
    private Long id;
    private Integer quantity;
    private Product product;
    private Double price;
    private Double TotalPrice;

    public OrderDetail(Long id, Integer quantity, Product product, Double price, Double totalPrice) {
        this.id = id;
        this.quantity = quantity;
        this.product = product;
        this.price = price;
        TotalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        TotalPrice = totalPrice;
    }
}
