package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Role {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "role_id", nullable = false)
    private int roleId;
    @Basic
    @Column(name = "rolename", nullable = false, length = 45)
    private String rolename;

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL)
    private List<Users> users;
}
