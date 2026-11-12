package com.luckermt.controller;

import com.luckermt.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.io.IOException;

@RestController
public class WeatherController {


    @GetMapping ("/get-weather")
    String sendWeather() throws FileNotFoundException {
        return WeatherService.sendJson();
    }

    @GetMapping("/data-send")
    void sendDudes() throws IOException {
        WeatherService.sendWithInterval();
    }
}
