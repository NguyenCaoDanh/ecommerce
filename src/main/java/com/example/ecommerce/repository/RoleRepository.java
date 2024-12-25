package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Role;
import com.example.ecommerce.generic.IRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends IRepository<Role,Integer> {
}
