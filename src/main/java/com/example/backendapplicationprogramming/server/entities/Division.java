package com.example.backendapplicationprogramming.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.Set;


@Data
@Entity
@Table(name = "divisions")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "division_id")
    private Long id;

    @Column(name = "division", length = 255)
    private String division_name;

    @Column(name = "create_date", columnDefinition = "DATETIME(6)")
    private Date create_date;

    @Column(name = "last_update", columnDefinition = "DATETIME(6)")
    private Date last_update;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "division")
    private Set<Customer> customers;
    @Transient
    private Links _links;

    public Division() {
    }

}
