package com.example.ecommerce.controller;

import com.example.ecommerce.dto.RequestResponse;
import com.example.ecommerce.entity.Categories;
import com.example.ecommerce.exception.ErrorHandler;
import com.example.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    // Helper method to create a standardized response
    private RequestResponse createResponse(String message, Object data) {
        return new RequestResponse(LocalDateTime.now().toString(), message, data);
    }
    @PreAuthorize("hasRole('ADMIN')")
    // Thêm Category
    @PostMapping("/add")
    public ResponseEntity<?> addCategory(@RequestBody Categories categories) {
        try {
            categoryService.save(categories);
            return ResponseEntity.ok(createResponse("Category added successfully!", categories));
        } catch (Exception e) {
            throw new ErrorHandler(HttpStatus.BAD_REQUEST, "Failed to add category: " + e.getMessage());
        }
    }

}
