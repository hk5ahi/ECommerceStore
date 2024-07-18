package com.example.backendapplicationprogramming.server.entities;

import com.example.backendapplicationprogramming.server.dto.ExcursionDTO;
import lombok.Data;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "vacations")
public class Vacation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vacation_id")
    private Long id;

    @Column(name = "vacation_title", length = 255)
    private String vacation_title;

    @Column(name = "description", length = 255)
    private String description;

    @Column(name = "travel_fare_price", precision = 19, scale = 2)
    private BigDecimal travel_price;

    @Column(name = "image_url", length = 255)
    private String image_URL;

    @Column(name = "create_date", columnDefinition = "DATETIME(6)")
    private Date create_date;

    @Column(name = "last_update", columnDefinition = "DATETIME(6)")
    private Date last_update;

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.ALL)
    private Set<Excursion> excursions = new HashSet<>();

    @Transient
    private Links _links;
    public Vacation() {

    }
    public Set<ExcursionDTO> getExcursions() {
        return this.excursions.stream().map(ExcursionDTO::new).collect(Collectors.toSet());
    }
}
