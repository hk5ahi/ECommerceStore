package com.example.backendapplicationprogramming.server.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;
import java.util.Set;

@Data
@Entity
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    @Column(name = "country", length = 255)
    private String country_name;

    @Column(name = "create_date", columnDefinition = "DATETIME(6)")
    private Date create_date;

    @Column(name = "last_update", columnDefinition = "DATETIME(6)")
    private Date last_update;

    @OneToMany(mappedBy = "country")
    private Set<Division> divisions;

    @Transient
    private Links _links;

}
