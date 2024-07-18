package com.example.backendapplicationprogramming.server.services.Impl;

import com.example.backendapplicationprogramming.server.config.AppConfig;
import com.example.backendapplicationprogramming.server.dao.VacationRepository;
import com.example.backendapplicationprogramming.server.dto.VacationDTO;
import com.example.backendapplicationprogramming.server.entities.Links;
import com.example.backendapplicationprogramming.server.entities.Vacation;
import com.example.backendapplicationprogramming.server.services.VacationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VacationServiceImpl implements VacationService {

    private final VacationRepository vacationRepository;
    private final AppConfig appConfig;

    public VacationServiceImpl(VacationRepository vacationRepository, AppConfig appConfig) {
        this.vacationRepository = vacationRepository;
        this.appConfig = appConfig;
    }

    @Override
    public Optional<VacationDTO> getVacationById(Long id) {
        Optional<Vacation> optionalVacation= vacationRepository.findById(id);
        if (optionalVacation.isEmpty()) {
            return Optional.empty();
        } else {
            Vacation vacation = optionalVacation.get();
            VacationDTO vacationDTO = new VacationDTO();
            vacationDTO.setId(vacation.getId());
            vacationDTO.setVacation_title(vacation.getVacation_title());
            vacationDTO.setDescription(vacation.getDescription());
            vacationDTO.setTravel_price(vacation.getTravel_price());
            vacationDTO.setImage_URL(vacation.getImage_URL());
            vacationDTO.setCreate_date(vacation.getCreate_date());
            vacationDTO.setLast_update(vacation.getLast_update());
            vacationDTO.setExcursions(vacation.getExcursions());
            setVacationLinks(vacation);
            vacationDTO.set_links(vacation.get_links());
            return Optional.of(vacationDTO);
        }
    }

    @Override
    public List<Vacation> getAllVacations() {
        List<Vacation> vacations= vacationRepository.findAll();
        for (Vacation vacation : vacations) {
            setVacationLinks(vacation);
        }
        return vacations;
    }
    public Optional<String> getVacationTitleById(Long id) {
        return vacationRepository.findById(id).map(Vacation::getVacation_title);
    }
    private void setVacationLinks(Vacation vacation) {
        String apiBaseUrl = appConfig.getBaseVacationURL();
        String selfHref = apiBaseUrl + "/" + vacation.getId();
        vacation.set_links(new Links(new Links.Self(selfHref)));
    }
}
