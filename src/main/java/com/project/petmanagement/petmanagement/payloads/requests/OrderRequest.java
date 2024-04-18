package com.project.petmanagement.petmanagement.payloads.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.petmanagement.petmanagement.models.enums.PaymentMethodEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderRequest {
    @NotEmpty(message = "Shipping Address is required.")
    @JsonProperty("shipping_address")
    private String shippingAddress;

    @NotEmpty(message = "Phone is required to contact with customer.")
    private String phone;

    @NotNull(message = "Payment Method is required.")
    @JsonProperty("payment_method")
    private PaymentMethodEnum paymentMethod;
}
