package com.example.backendapplicationprogramming.server.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @Column(name = "customer_first_name", length = 255)
    private String firstName;

    @Column(name = "customer_last_name", length = 255)
    private String lastName;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "postal_code", length = 255)
    private String postal_code;

    @Column(name = "phone", length = 255)
    private String phone;

    @Column(name = "create_date", columnDefinition = "DATETIME(6)")
    private Date create_date;

    @Column(name = "last_update", columnDefinition = "DATETIME(6)")
    private Date last_update;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "division_id")
    private Division division;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<Cart> carts = new HashSet<>();

    public Customer() {
    }
}
