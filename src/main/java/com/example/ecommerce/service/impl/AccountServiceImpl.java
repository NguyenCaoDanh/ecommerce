package com.example.ecommerce.service.impl;


import com.example.ecommerce.entity.Role;
import com.example.ecommerce.entity.Users;
import com.example.ecommerce.exception.ErrorHandler;
import com.example.ecommerce.generic.IRepository;
import com.example.ecommerce.repository.RoleRepository;
import com.example.ecommerce.repository.UsersRepository;
import com.example.ecommerce.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public IRepository<Users, Integer> getRepository() {
        return usersRepository;
    }

    @Override
    public void save(Users users) {
        try {
            // Lấy role với role_id = 1 từ database
            Role role = roleRepository.findById(1)
                    .orElseThrow(() -> new ErrorHandler(HttpStatus.BAD_REQUEST, "Role with ID 1 not found"));

            // Gán role và các thuộc tính khác
            users.setRole(role);
            users.setPassword(passwordEncoder.encode(users.getPassword()));

            // Lưu đối tượng users
            usersRepository.save(users);
        } catch (Exception e) {
            throw new ErrorHandler(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public Iterator<Users> findAll() {
        return null;
    }

    @Override
    public Users findOne(Integer integer) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> account = usersRepository.findByUsername(username);
        return account.orElseThrow(() -> new ErrorHandler(HttpStatus.UNAUTHORIZED, "Account not exist"));
    }



}