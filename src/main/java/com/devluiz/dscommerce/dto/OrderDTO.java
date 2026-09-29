package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.Order;
import com.devluiz.dscommerce.entities.OrderItem;
import com.devluiz.dscommerce.entities.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import org.aspectj.bridge.IMessage;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public record OrderDTO(
        @Schema(description = "Identificador único do pedido", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
        Long id,
        @Schema(description = "Instante em que o pedido foi registrado (Padrão UTC)", example = "2026-09-29T14:35:13Z", accessMode = Schema.AccessMode.READ_ONLY)
        Instant moment,
        @Schema(description = "Status atual do pedido", example = "WAITING_PAYMENT", accessMode = Schema.AccessMode.READ_ONLY)
        OrderStatus status,
        @Schema(description = "Dados resumidos do cliente que realizou o pedido", accessMode = Schema.AccessMode.READ_ONLY)
        UserMinDTO client,
        @Schema(description = "Dados do pagamento, caso exista", accessMode = Schema.AccessMode.READ_ONLY)
        PaymentDTO payment,
        @NotEmpty(message = "A lista deve ter pelo menos um item.")
        @Schema(description = "Lista de itens comprados no pedido (Carrinho de compras)")
        List<OrderItemDTO2> items
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
            OrderItemDTO2 dto = new OrderItemDTO2(item);
            this.items.add(dto);
        }
    }
    @Schema(description = "Valor total do pedido (Soma dos subtotais dos itens)", example = "4500.00", accessMode = Schema.AccessMode.READ_ONLY)
    public BigDecimal getTotal() {
        BigDecimal sum = BigDecimal.ZERO;
        for (OrderItemDTO2 item : items) {
            sum = sum.add(item.subTotal());
        }
        return sum;
    }
}
