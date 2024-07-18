package com.example.backendapplicationprogramming.server.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "excursions")
public class Excursion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "excursion_id")
    private Long id;

    @Column(name = "excursion_title", length = 255)
    private String excursion_title;

    @Column(name = "excursion_price", precision = 19, scale = 2)
    private BigDecimal excursion_price;

    @Column(name = "image_url", length = 255)
    private String image_URL;

    @Column(name = "create_date", columnDefinition = "DATETIME(6)")
    private Date create_date;

    @Column(name = "last_update", columnDefinition = "DATETIME(6)")
    private Date last_update;

    @ManyToOne
    @JoinColumn(name = "vacation_id")
    private Vacation vacation;

    @ManyToMany(mappedBy = "excursions",fetch = FetchType.LAZY)
    private Set<CartItem> cartItems = new HashSet<>() ;
    @Transient
    private Links _links;
    public Excursion() {
    }
}
