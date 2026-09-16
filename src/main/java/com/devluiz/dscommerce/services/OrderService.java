package com.devluiz.dscommerce.services;

import com.devluiz.dscommerce.dto.OrderDTO;
import com.devluiz.dscommerce.dto.OrderItemDTO;
import com.devluiz.dscommerce.dto.ProductDTO2;
import com.devluiz.dscommerce.entities.*;
import com.devluiz.dscommerce.repositories.OrderItemRepository;
import com.devluiz.dscommerce.repositories.OrderRepository;
import com.devluiz.dscommerce.repositories.ProductRepository;
import com.devluiz.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id){
        Order order = orderRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado")
        );
        return new OrderDTO(order);

    }

    @Transactional
    public OrderDTO insert(OrderDTO dto){
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        User user = userService.authenticated();
        order.setClient(user);
        for (OrderItemDTO itemDto : dto.items()){
            Product product = productRepository.getReferenceById(itemDto.productId());
            OrderItem item = new OrderItem(order,product, itemDto.quantity(), product.getPrice());
            order.getItems().add(item);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());

        return new OrderDTO(order);
    }
}
