package com.project.petmanagement.models;

import java.util.Date;
import java.util.List;

public class Order {
    private Long id;
    private Double totalPrice;
    private Date orderDate;
    private String status;
    private String address;
    private List<OrderDetail> orderDetais;

    public Order(Long id, Double totalPrice, String status, List<OrderDetail> orderDetais) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.orderDetais = orderDetais;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<OrderDetail> getOrderDetais() {
        return orderDetais;
    }

    public void setOrderDetais(List<OrderDetail> orderDetais) {
        this.orderDetais = orderDetais;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
