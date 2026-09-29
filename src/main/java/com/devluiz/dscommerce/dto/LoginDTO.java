package com.devluiz.dscommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record LoginDTO(

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "O e-mail deve ser válido")
        @Schema(description = "E-mail do usuário", example = "alex@gmail.com")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Schema(description = "Senha para autenticação", example = "123456")
        String password
) {}
