package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Products;
import com.example.ecommerce.generic.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends IRepository<Products,Integer> {
}
