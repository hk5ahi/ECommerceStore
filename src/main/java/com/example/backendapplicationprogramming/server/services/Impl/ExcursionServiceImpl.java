package com.example.backendapplicationprogramming.server.services.Impl;

import com.example.backendapplicationprogramming.server.config.AppConfig;
import com.example.backendapplicationprogramming.server.dao.ExcursionRepository;
import com.example.backendapplicationprogramming.server.dto.ExcursionDTO;
import com.example.backendapplicationprogramming.server.entities.Excursion;
import com.example.backendapplicationprogramming.server.entities.Links;
import com.example.backendapplicationprogramming.server.services.ExcursionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExcursionServiceImpl implements ExcursionService {

    private final ExcursionRepository excursionRepository;
    private final AppConfig appConfig;

    public ExcursionServiceImpl(ExcursionRepository excursionRepository, AppConfig appConfig) {
        this.excursionRepository = excursionRepository;
        this.appConfig = appConfig;
    }

    public Optional<ExcursionDTO> getExcursionByIdAndVacationId(Long vacationId, Long excursionId) {
        Optional<Excursion> optionalExcursion = excursionRepository.findByIdAndVacationId(excursionId, vacationId);
        optionalExcursion.ifPresent(this::setExcursionLinks);
        ExcursionDTO excursionDTO = optionalExcursion.map(this::convertToDto).orElse(null);
        return Optional.ofNullable(excursionDTO);
    }

    public List<Excursion> getAllExcursionsByVacationId(Long vacationId) {
        List<Excursion> excursions = excursionRepository.findByVacationId(vacationId);
        if (!excursions.isEmpty()) {
            for (Excursion excursion : excursions) {
                setExcursionLinks(excursion);
            }
        }
        return excursions;
    }

    private void setExcursionLinks(Excursion excursion) {
        String apiBaseUrl = appConfig.getBaseExcursionURL();
        String selfHref = apiBaseUrl + "/" + excursion.getId();
        excursion.set_links(new Links(new Links.Self(selfHref)));
    }

    private ExcursionDTO convertToDto(Excursion excursion) {
        // Perform mapping from Customer entity to CustomerDTO
        ExcursionDTO excursionDTO = new ExcursionDTO();
        excursionDTO.setId(excursion.getId());
        excursionDTO.setExcursion_title(excursion.getExcursion_title());
        excursionDTO.setExcursion_price(excursion.getExcursion_price());
        excursionDTO.setImage_URL(excursion.getImage_URL());
        excursionDTO.setCreate_date(excursion.getCreate_date());
        excursionDTO.setLast_update(excursion.getLast_update());
        excursionDTO.set_links(excursion.get_links());
        return excursionDTO;
    }
}
