package com.laarc.hoamanagerserver.config;

import com.laarc.hoamanagerserver.api.config.ApiConfigProperties;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

@Configuration
@EnableWebMvc
@EnableConfigurationProperties({
        ApiConfigProperties.class
})
public class ApplicationConfig {
    @Value("${jwt.secret-key}")
    private String jwtSecretKey;

    // BEAN DEFINITIONS
    /**
     * @return Model mapper to map entities to DTOs and vice-versa
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SecretKey secretKey() {
        return new SecretKeySpec(jwtSecretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
    }

}
