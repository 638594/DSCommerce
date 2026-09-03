package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.Category;

public record CategoryDTO(
        Long id,
        String name
) {

    public CategoryDTO(Category entity){
        this(
                entity.getId(),
                entity.getName()
        );
    }
}
