package com.example.ecommerce.service;

import com.example.ecommerce.entity.Users;
import com.example.ecommerce.generic.IRepository;
import com.example.ecommerce.generic.IService;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AccountService  extends IService<Users, Integer>, UserDetailsService {
    IRepository<Users, Integer> getRepository();
}
