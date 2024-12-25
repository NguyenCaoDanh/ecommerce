package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@Entity
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "order_id", nullable = false)
    private int orderId;

    // Thuộc tính này chỉ là ánh xạ đơn giản tới cột "user_id", không tham gia quản lý dữ liệu
    @Basic
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    private int userId;

    @Basic
    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    @Basic
    @Column(name = "status", nullable = true, length = 45)
    private String status;

    @Basic
    @Column(name = "created_at", nullable = true)
    private Timestamp createdAt;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Liên kết với Users qua cột "user_id"
    private Users users;
}



