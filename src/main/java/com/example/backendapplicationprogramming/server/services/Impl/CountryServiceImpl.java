package com.example.backendapplicationprogramming.server.services.Impl;

import com.example.backendapplicationprogramming.server.dao.CountryRepository;
import com.example.backendapplicationprogramming.server.dto.CountryDTO;
import com.example.backendapplicationprogramming.server.entities.Country;
import com.example.backendapplicationprogramming.server.services.CountryService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository)
    {
        this.countryRepository=countryRepository;
    }

    @Override
    public Map<String, Object> getAllCountries() {
        List<Country> countries = countryRepository.findAll();
        List<CountryDTO> countryDTOS = countries.stream().map(country -> {
            CountryDTO countryDTO = new CountryDTO();
            countryDTO.setCountry_name(country.getCountry_name());
            countryDTO.setId(country.getId());
            countryDTO.set_links(country.get_links());
            return countryDTO;
        }).collect(Collectors.toList());
        // Create a map to embed the countries list with the correct key
        Map<String, Object> embedded = new HashMap<>();
        embedded.put("countries", countryDTOS); // Use "countries" as the key

        CollectionModel<CountryDTO> resource = CollectionModel.of(countryDTOS)
                .add(Link.of("/api/countries").withSelfRel());

        // Create a new response with the embedded map
        Map<String, Object> response = new HashMap<>();
        response.put("_embedded", embedded);
        response.put("_links", resource.getLinks());
        return response;
    }
    @Override
    public Country getCountryById(String country_URL) {
        Long id = Long.parseLong(country_URL.substring(country_URL.lastIndexOf('/') + 1));
        return countryRepository.findById(id).orElse(null);
    }
}
