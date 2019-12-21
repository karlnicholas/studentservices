package com.github.karlnicholas.ss.studentservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.karlnicholas.ss.model.Student;

import reactor.core.publisher.Flux;

@SpringBootApplication
@RestController
@RequestMapping("ss")
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
	@GetMapping("student")
	Flux<Student> getStudents(@RequestParam String school) {
		if ( school.equalsIgnoreCase("USC")) { 
			return Flux.just(new Student("S1"), new Student("S2"));
		} else {
			return Flux.just(new Student("S3"), new Student("S4"));
		}
	}
}
