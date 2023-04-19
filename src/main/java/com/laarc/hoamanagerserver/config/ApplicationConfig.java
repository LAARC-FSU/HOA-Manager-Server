package com.laarc.hoamanagerserver.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    // BEAN DEFINITIONS
    /**
     * @return Model mapper to map entities to DTOs and vice-versa
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
