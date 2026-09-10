package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.Payment;

import java.time.Instant;

public record PaymentDTO(
        Long id,
        Instant moment
) {

    public PaymentDTO(Payment entity) {
        this(
                entity.getId(),
                entity.getMoment()
        );
    }
}
