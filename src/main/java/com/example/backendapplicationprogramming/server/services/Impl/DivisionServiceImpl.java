package com.example.backendapplicationprogramming.server.services.Impl;

import com.example.backendapplicationprogramming.server.dao.DivisionRepository;
import com.example.backendapplicationprogramming.server.dto.DivisionDTO;
import com.example.backendapplicationprogramming.server.entities.Division;
import com.example.backendapplicationprogramming.server.services.DivisionService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DivisionServiceImpl implements DivisionService {

    private final DivisionRepository divisionRepository;

    public DivisionServiceImpl(DivisionRepository divisionRepository)
    {
        this.divisionRepository=divisionRepository;
    }

    @Override
    public Map<String, Object> getAllDivisions() {
        List<Division> divisions = divisionRepository.findAll();
        List<DivisionDTO> divisionDTOS = divisions.stream().map(division -> {
            DivisionDTO divisionDTO = new DivisionDTO();
            divisionDTO.setDivision_name(division.getDivision_name());
            divisionDTO.setId(division.getId());
            divisionDTO.set_links(division.get_links());
            divisionDTO.setCountry_id(division.getCountry().getId());
            return divisionDTO;
        }).collect(Collectors.toList());
        // Create a map to embed the divisions list with the correct key
        Map<String, Object> embedded = new HashMap<>();
        embedded.put("divisions", divisionDTOS); // Use "divisions" as the key

        CollectionModel<DivisionDTO> resource = CollectionModel.of(divisionDTOS)
                .add(Link.of("/api/divisions").withSelfRel());

        // Create a new response with the embedded map
        Map<String, Object> response = new HashMap<>();
        response.put("_embedded", embedded);
        response.put("_links", resource.getLinks());
        return response;
    }

    @Override
    public Division getDivisionById(String division_URL) {
        Long id = Long.parseLong(division_URL.substring(division_URL.lastIndexOf('/') + 1));
        return divisionRepository.findById(id).orElse(null);
    }
}
