package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.Order;
import com.devluiz.dscommerce.entities.OrderItem;
import com.devluiz.dscommerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;
import org.aspectj.bridge.IMessage;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record OrderDTO(
        Long id,
        Instant moment,
        OrderStatus status,
        UserMinDTO client,
        PaymentDTO payment,
        @NotEmpty(message = "A lista deve ter pelo menos um item.")
        List<OrderItemDTO> items
) {

    public OrderDTO(Order entity) {
        this(
                entity.getId(),
                entity.getMoment(),
                entity.getStatus(),
                new UserMinDTO(entity.getClient()),
                (entity.getPayment() == null) ? null : new PaymentDTO(entity.getPayment()),
                new ArrayList<>()
        );

        for (OrderItem item : entity.getItems()) {
            OrderItemDTO dto = new OrderItemDTO(item);
            this.items.add(dto);
        }
    }

    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItemDTO item : items) {
            sum = sum.add(item.getSubTotal());
        }
        return sum;
    }
}
