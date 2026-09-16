package com.devluiz.dscommerce.repositories;

import com.devluiz.dscommerce.entities.OrderItem;
import com.devluiz.dscommerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
