package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.Category;
import io.swagger.v3.oas.annotations.media.Schema;

public record CategoryDTO(
        @Schema(description = "Código de identificação da categoria", example = "1")
        Long id,
        @Schema(accessMode = Schema.AccessMode.READ_ONLY)
        String name
) {

    public CategoryDTO(Category entity){
        this(
                entity.getId(),
                entity.getName()
        );
    }
}
