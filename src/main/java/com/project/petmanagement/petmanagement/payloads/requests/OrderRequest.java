package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.petmanagement.petmanagement.models.entity.OrderDetail;
import com.project.petmanagement.petmanagement.models.entity.User;
import com.project.petmanagement.petmanagement.models.enums.OrderStatusEnum;
import com.project.petmanagement.petmanagement.models.enums.PaymentMethodEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRequest {
    @NotEmpty(message = "shipping address is require.")
    @JsonProperty("shipping_address")
    private String shippingAddress;
    @NotEmpty(message = "phone is require.")
    private String phone;
    @NotNull(message = "Payment method is require.")
    @JsonProperty("payment_method")
    private PaymentMethodEnum paymentMethod;

}
