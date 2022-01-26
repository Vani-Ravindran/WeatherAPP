package com.weather.app.service;

import java.net.URI;
import java.net.URLEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriTemplate;

@Service
public class WeatherService {
	@Value("${api.openweathermap.appKey}")
    private String apiKey;
	@Autowired
	RestTemplate restTemplate;
	@Cacheable("ClimateDetails")
	public ResponseEntity<String> callWeatherApiByCity(String cityName,String countryCode) {
		final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?q={city},{country}&APPID={key}";
        URI url = new UriTemplate(WEATHER_URL).expand(cityName, countryCode, apiKey);
		ResponseEntity<String> climateDetails=restTemplate.getForEntity( url , String.class);
		return climateDetails;
		
		
	}
	@Cacheable("ClimateDetails")
	public ResponseEntity<String> callWeatherApiByCoordnates(String lat,String lon) {
		final String WEATHER_URL = "http://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&appid={API key}";
        URI url = new UriTemplate(WEATHER_URL).expand(lat, lon, apiKey);
		ResponseEntity<String> climateDetails=restTemplate.getForEntity( url , String.class);
		return climateDetails;
		
		
	}

}
