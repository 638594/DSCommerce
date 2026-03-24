package com.devluiz.dscommerce.services;

import com.devluiz.dscommerce.dto.ProductDTO;
import com.devluiz.dscommerce.entities.Product;
import com.devluiz.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id){
        Product product = productRepository.findById(id).get();
        return new ProductDTO(product);

    }
}
