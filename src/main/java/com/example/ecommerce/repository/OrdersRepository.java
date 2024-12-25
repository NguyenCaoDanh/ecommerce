package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Orders;
import com.example.ecommerce.generic.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends IRepository<Orders,Integer> {
}
