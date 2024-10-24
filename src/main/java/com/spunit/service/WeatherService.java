package com.spunit.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getWeather(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(apiUrl)
                .queryParam("q", city)
                .queryParam("appid", apiKey)
                .queryParam("units", "metric")
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}
