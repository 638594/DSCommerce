package com.devluiz.dscommerce.controllers.handlers;

import com.devluiz.dscommerce.dto.OrderDTO;
import com.devluiz.dscommerce.dto.ProductDTO2;
import com.devluiz.dscommerce.dto.ProductMinDTO;
import com.devluiz.dscommerce.services.OrderService;
import com.devluiz.dscommerce.services.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable("id") Long id){
        OrderDTO dto = orderService.findById(id);
        return ResponseEntity.ok(dto);
    }


}
