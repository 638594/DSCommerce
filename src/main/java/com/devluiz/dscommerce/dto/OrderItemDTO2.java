package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.OrderItem;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Item individual que compõe o carrinho de compras do pedido")
public record OrderItemDTO2(
        @Schema(description = "ID do produto adicionado ao pedido", example = "1")
        Long productId,
        @Schema(description = "Nome do produto", example = "Monitor UltraWide LG", accessMode = Schema.AccessMode.READ_ONLY)
        String name,
        @Schema(description = "Preço unitário do produto gravado no momento da compra", example = "2500.00", accessMode = Schema.AccessMode.READ_ONLY)
        BigDecimal price,
        @Schema(description = "Quantidade de unidades compradas", example = "2")
        Integer quantity,
        @Schema(description = "Subtotal do item (preço * quantidade)", example = "5000.00", accessMode = Schema.AccessMode.READ_ONLY)
        BigDecimal subTotal
) {

    public OrderItemDTO2(OrderItem entity){
        this(
                entity.getProduct().getId(),
                entity.getProduct().getName(),
                entity.getPrice(),
                entity.getQuantity(),
                entity.getPrice().multiply(BigDecimal.valueOf((entity.getQuantity())))
        );
    }

}
