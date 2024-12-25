package com.example.ecommerce.repository;

import com.example.ecommerce.entity.OrderDetails;
import com.example.ecommerce.generic.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepository extends IRepository<OrderDetails,Integer> {
}
