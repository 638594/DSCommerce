package com.devluiz.dscommerce.services;

import com.devluiz.dscommerce.dto.OrderDTO;
import com.devluiz.dscommerce.dto.ProductDTO2;
import com.devluiz.dscommerce.entities.Order;
import com.devluiz.dscommerce.entities.Product;
import com.devluiz.dscommerce.repositories.OrderRepository;
import com.devluiz.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado")
        );
        return new OrderDTO(order);

    }
}
