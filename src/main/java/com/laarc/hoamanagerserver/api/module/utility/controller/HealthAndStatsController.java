package com.laarc.hoamanagerserver.api.module.utility.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class HealthAndStatsController {

    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public String rootApp() {
        return "HOA Manager Server";
    }

    @GetMapping("/health")
    @ResponseStatus(code = HttpStatus.OK)
    public String healthCheck() {
        return "Healthy!";
    }

}
