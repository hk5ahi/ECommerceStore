package com.example.backendapplicationprogramming.server.config;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AppConfig {
    @Value("${api.base.vacation}")
    private String baseVacationURL;
    @Value("${api.base.excursion}")
    private String baseExcursionURL;

}
