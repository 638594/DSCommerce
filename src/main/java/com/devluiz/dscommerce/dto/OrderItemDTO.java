package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.OrderItem;

import java.math.BigDecimal;

public record OrderItemDTO(
        Long productId,
        String name,
        BigDecimal price,
        Integer quantity
) {

    public OrderItemDTO(OrderItem entity){
        this(
                entity.getProduct().getId(),
                entity.getProduct().getName(),
                entity.getPrice(),
                entity.getQuantity()
        );
    }

    public BigDecimal getSubTotal(){
        if(price == null || quantity == null){
            return BigDecimal.ZERO;
        }
        return price.multiply(BigDecimal.valueOf(quantity));
    }
}
