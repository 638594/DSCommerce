package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.User;

public record UserMinDTO(
        Long id,
        String name
) {

    public UserMinDTO(User entity){
        this(
                entity.getId(),
                entity.getName()
        );
    }
}
