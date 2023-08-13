package com.RestApi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.RestApi.binding.Passenger;
import com.RestApi.service.MTMserviceImpl;

@SpringBootApplication
public class MakeMyTrip1Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(MakeMyTrip1Application.class, args);

		
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}