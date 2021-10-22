package com.dock.tech.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dock.tech.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
