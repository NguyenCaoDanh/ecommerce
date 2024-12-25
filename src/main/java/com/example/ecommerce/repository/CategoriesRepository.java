package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Categories;
import com.example.ecommerce.generic.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends IRepository<Categories,Integer> {
}
