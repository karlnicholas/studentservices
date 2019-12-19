package com.github.karlnicholas.ss.schoolservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.github.karlnicholas.ss.model.School;

import reactor.core.publisher.Flux;

@SpringBootApplication
@EnableWebFlux
@RestController
@RequestMapping("ss")
public class SchoolServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolServiceApplication.class, args);
	}
	@GetMapping("school")
	Flux<School> getSchool(@RequestParam String state) {
		return Flux.just(new School("USC"), new School("Stanford"));
	}


}
