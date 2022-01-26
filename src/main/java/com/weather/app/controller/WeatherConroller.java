package com.weather.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weather.app.service.WeatherService;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("api/v1/weather")
public class WeatherConroller {
	@Autowired
	private WeatherService weatherService;
	@Operation(summary="Weather detils based on city")
	@GetMapping("/city/{cityName}/{countryCode}")
	public ResponseEntity<String> getWeatherbyCity(@PathVariable("cityName") String cityName, @PathVariable("countryCode") String countryCode)
	{
		ResponseEntity<String> respone=weatherService.callWeatherApiByCity(cityName,countryCode);
		return respone;
		
	}
	@Operation(summary="Weather detils based on coordinates")
	@GetMapping("{lat}/{lon}")
	public ResponseEntity<String> getWeatherbyCoordinates(@PathVariable("lat") String lat, @PathVariable("lon") String lon)
	{
		ResponseEntity<String> respone=weatherService.callWeatherApiByCoordnates(lat,lon);
		return respone;
		
	}

}
