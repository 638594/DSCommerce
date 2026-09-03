package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.Category;
import com.devluiz.dscommerce.entities.Product;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record ProductDTO2(
        Long id,
        String name,
        String description,
        Double price,
        String imgUrl,
        @NotEmpty(message = "Deve ter pelo menos uma categoria.")
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
