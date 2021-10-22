package com.dock.tech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dock.tech.entities.OrderItem;
import com.dock.tech.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
