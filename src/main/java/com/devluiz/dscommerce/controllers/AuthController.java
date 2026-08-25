package com.devluiz.dscommerce.controllers;

import com.devluiz.dscommerce.config.security.JwtUtil;
import com.devluiz.dscommerce.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

    @RequestMapping(value = "/login")
    public ResponseEntity<String> login (@RequestBody LoginDTO dto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(),dto.password())
        );

        // Se passar, gera o token JWT assinado
        String token = jwtUtil.generateToken(authentication);

        return ResponseEntity.ok(token);
    }
}
