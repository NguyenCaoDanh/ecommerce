package com.example.ecommerce.repository;

import com.example.ecommerce.entity.Users;
import com.example.ecommerce.generic.IRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends IRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);
}
