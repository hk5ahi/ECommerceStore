package com.example.backendapplicationprogramming.server.controllers;

import com.example.backendapplicationprogramming.server.dto.ExcursionDTO;
import com.example.backendapplicationprogramming.server.dto.VacationDTO;
import com.example.backendapplicationprogramming.server.entities.Excursion;
import com.example.backendapplicationprogramming.server.entities.Vacation;
import com.example.backendapplicationprogramming.server.services.ExcursionService;
import com.example.backendapplicationprogramming.server.services.VacationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/vacations")
public class vacationController {

    @Autowired
    private VacationService vacationService;
    private final ExcursionService excursionService;

    @Autowired
    public vacationController(VacationService vacationService, ExcursionService excursionService) {
        this.vacationService = vacationService;
        this.excursionService = excursionService;

    }

    @GetMapping("/{id}")
    public Optional<VacationDTO> getVacation(@PathVariable Long id) {
        return vacationService.getVacationById(id);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<Vacation>> getAllVacations() {
        List<Vacation> vacations = vacationService.getAllVacations();
        CollectionModel<Vacation> resource = CollectionModel.of(vacations)
                .add(linkTo(WebMvcLinkBuilder.methodOn(vacationController.class).getAllVacations()).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{vacationId}/excursions")
    public ResponseEntity<CollectionModel<Excursion>> getAllExcursions(@PathVariable Long vacationId) {
        List<Excursion> excursions = excursionService.getAllExcursionsByVacationId(vacationId);
        if (excursions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        CollectionModel<Excursion> resource = CollectionModel.of(excursions)
                .add(linkTo(methodOn(vacationController.class).getAllExcursions(vacationId)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @GetMapping("/{vacationId}/title")
    public ResponseEntity<String> getVacationTitle(@PathVariable Long vacationId) {
        return vacationService.getVacationTitleById(vacationId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{vacationId}/excursions/{excursionId}")
    public ResponseEntity<ExcursionDTO> getExcursion(@PathVariable Long vacationId, @PathVariable Long excursionId) {
        Optional<ExcursionDTO> optionalExcursion = excursionService.getExcursionByIdAndVacationId(vacationId, excursionId);
        return optionalExcursion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
