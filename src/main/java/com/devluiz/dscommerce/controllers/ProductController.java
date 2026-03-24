package com.devluiz.dscommerce.controllers;

import com.devluiz.dscommerce.dto.ProductDTO;
import com.devluiz.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDTO findById(@PathVariable("id") Long id){
        return productService.findById(id);

    }

    @GetMapping
    public Page<ProductDTO> findAll(Pageable pageable){
        return productService.findAll(pageable);
    }

    @PostMapping
    public ProductDTO insert(@RequestBody ProductDTO dto){
        return productService.insert(dto);
    }
}
