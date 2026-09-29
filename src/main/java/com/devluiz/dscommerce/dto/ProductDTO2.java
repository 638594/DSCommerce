package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.Category;
import com.devluiz.dscommerce.entities.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@Schema(description = "Objeto que representa um produto no catálogo")
public record ProductDTO2(
        @Schema(description = "Identificador único do produto (Gerado automaticamente)", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
        Long id,
        @Schema(description = "Nome do produto", example = "Monitor UltraWide")
        @NotBlank(message = "Campo requerido")
        @Size(min = 3, max = 80, message = "Nome precisa ter entre 3 e 80 caracteres")
        String name,
        @Schema(description = "Descrição detalhada do produto", example = "Monitor ideal para programação e produtividade, tela curva, 144Hz.")
        @NotBlank(message = "Campo requerido")
        @Size(min = 10, message = "Descricao precisa ter no minimo 10 caracteres")
        String description,
        @Schema(description = "Preço do produto", example = "2500.00")
        @Positive(message = "Valor do preço deve ser positivo")
        BigDecimal price,
        @Schema(description = "URL da imagem principal do produto", example = "https://exemplo.com/imagens/monitor-lg.jpg")
        String imgUrl,
        @NotEmpty(message = "Deve ter pelo menos uma categoria.")
        @Schema(description = "Lista de categorias às quais o produto pertence")
        Set<CategoryDTO> categories
) {

    public ProductDTO2(Product entity){
        this(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getImgUrl(),
                entity.getCategories()
                        .stream()
                        .map(CategoryDTO::new)
                        .collect(Collectors.toSet())

        );
    }
}
