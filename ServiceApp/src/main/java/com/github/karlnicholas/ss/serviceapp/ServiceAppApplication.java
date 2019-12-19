package com.github.karlnicholas.ss.serviceapp;

import java.net.URI;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.github.karlnicholas.ss.model.School;
import com.github.karlnicholas.ss.model.Student;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class ServiceAppApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ServiceAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		WebClient.create().get().uri(URI.create("http://localhost:8082/ss/school?state=CA"))
		.accept(MediaType.APPLICATION_JSON)
		.exchange()
		.flatMapMany(cr->cr.bodyToFlux(School.class))
		.flatMap(school->{
			return WebClient.create().get().uri(URI.create("http://localhost:8081/ss/student?school="+school.getName()))
			.accept(MediaType.APPLICATION_JSON)
			.exchange()
			.flatMapMany(crs->crs.bodyToFlux(Student.class))
			.collectList()
			.map(sl->{
				school.setStudents(sl);
				return school;
			});
		})
		.subscribe(System.out::println);
	}

}
