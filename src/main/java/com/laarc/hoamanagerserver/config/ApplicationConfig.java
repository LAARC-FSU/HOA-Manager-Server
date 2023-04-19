package com.laarc.hoamanagerserver.config;

import com.laarc.hoamanagerserver.api.config.ApiConfigProperties;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({
        ApiConfigProperties.class
})
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
