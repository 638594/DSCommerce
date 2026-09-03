package com.devluiz.dscommerce.dto;

import com.devluiz.dscommerce.entities.Role;
import com.devluiz.dscommerce.entities.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record UserDTO(

        Long id,
        @NotBlank(message = "Campo requirido.")
        @Size(min = 3, max = 80, message = "Nome precisa ter entre 3 e 80 caracteres.")
        String name,
        @Column(unique = true)
        @NotBlank(message = "Campo requirido.")
        String email,
        String phone,
        LocalDate birthDate,
        List<String> roles
    ) {

    public UserDTO(User entity){
        this(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getPhone(),
                entity.getBirthDate(),
                entity.getRoles()
                        .stream()
                        .map(Role::getAuthority)
                        .toList()
        );
    }

}
