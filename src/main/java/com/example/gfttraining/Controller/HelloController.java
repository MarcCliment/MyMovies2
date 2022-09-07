package com.example.gfttraining.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gfttraining.Service.MovieDBService;

import java.util.HashMap;

@RestController
public class HelloController {

    @Autowired
    MovieDBService movieDbService;

    @GetMapping("/")
    public String root() {
        return "Hola ke ase!";
    }

    @GetMapping("/api/configuration")
    public HashMap<String, Object> getConfiguration() {
        HashMap<String, Object> config = movieDbService.getConfig();
        config.put("hola","kease");
        return config;
    }
}
