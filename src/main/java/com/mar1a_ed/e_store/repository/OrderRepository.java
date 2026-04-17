package com.mar1a_ed.e_store.repository;

import com.mar1a_ed.e_store.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByClientCode(String clientCode);
}
