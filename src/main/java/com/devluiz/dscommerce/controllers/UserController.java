package com.devluiz.dscommerce.controllers;

import com.devluiz.dscommerce.dto.ProductDTO;
import com.devluiz.dscommerce.dto.UserDTO;
import com.devluiz.dscommerce.services.ProductService;
import com.devluiz.dscommerce.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/me")
    public ResponseEntity<UserDTO> getMe(){
        UserDTO dto = userService.getMe();
        return ResponseEntity.ok(dto);
    }


}
