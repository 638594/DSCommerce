package com.devluiz.dscommerce.controllers;

import com.devluiz.dscommerce.config.security.JwtUtil;
import com.devluiz.dscommerce.dto.LoginDTO;
import com.devluiz.dscommerce.dto.TokenDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @Operation(summary = "Realiza o login", description = "Autentica um usuário e retorna o token JWT de acesso.")
    @PostMapping(value = "/login")
    public ResponseEntity<TokenDTO> login (@Valid @RequestBody LoginDTO dto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(),dto.password())
        );

        // Se passar, gera o token JWT assinado
        String token = jwtUtil.generateToken(authentication);

        // Empacota a string dentro do record DTO
        TokenDTO tokenDTO = new TokenDTO(token);

        return ResponseEntity.ok(tokenDTO);
    }
}
