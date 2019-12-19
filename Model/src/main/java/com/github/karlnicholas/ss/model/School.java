package com.github.karlnicholas.ss.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class School {
	@NonNull
	String name;
	List<Student> students;
}
