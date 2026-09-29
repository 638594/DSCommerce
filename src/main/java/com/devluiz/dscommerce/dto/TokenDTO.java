package com.devluiz.dscommerce.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Objeto de resposta contendo o token de acesso")
public record TokenDTO(

        @Schema(description = "Token JWT gerado após o login", example = "eyJhbGciOiJIUzI1...")
        String accessToken
) {
}
