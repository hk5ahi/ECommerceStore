package com.example.backendapplicationprogramming.server.dto;

import com.example.backendapplicationprogramming.server.entities.Excursion;
import com.example.backendapplicationprogramming.server.entities.Links;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class ExcursionDTO {

    private String excursion_title;
    private BigDecimal excursion_price;
    private String image_URL;
    private Date create_date;
    private Date last_update;
    private Links _links;
    private long id;

    public ExcursionDTO(Excursion excursion) {
        this.id = excursion.getId();
        this.excursion_title = excursion.getExcursion_title();
        this.excursion_price = excursion.getExcursion_price();
        this.image_URL = excursion.getImage_URL();
        this.create_date = excursion.getCreate_date();
        this.last_update = excursion.getLast_update();
        this._links = excursion.get_links();
    }
    public ExcursionDTO() {
    }
}
