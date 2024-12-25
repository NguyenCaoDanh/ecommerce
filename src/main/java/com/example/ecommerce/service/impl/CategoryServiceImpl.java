package com.example.ecommerce.service.impl;

import com.example.ecommerce.entity.Categories;
import com.example.ecommerce.repository.CategoriesRepository;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoriesRepository categoriesRepository;
    @Override
    public void save(Categories categories) {
        try {
            categoriesRepository.save(categories);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Iterator<Categories> findAll() {
        return null;
    }

    @Override
    public Categories findOne(Integer integer) {
        return null;
    }
}
