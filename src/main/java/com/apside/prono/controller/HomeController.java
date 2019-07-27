package com.apside.prono.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/")
public class HomeController {
    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    /**
     * GET  / home controller.
     *
     * @return une phrase :)
     */
    @GetMapping("/")
    public String home() {
        return "Bienvenue sur le site de pronostics";
    }
}
