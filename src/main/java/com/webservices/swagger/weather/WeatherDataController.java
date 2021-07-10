package com.webservices.swagger.weather;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "api/weather/v2")
@Api(description = "Latitude/Longitude weather history", tags = "Weather Data")
public class WeatherDataController {

    @GetMapping("/points/")
    public void getWeatherHistory() {

    }
    
}
