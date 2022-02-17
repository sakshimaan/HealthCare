package com.healthcare.patient

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PatientApplication

fun main(args: Array<String>) {
	runApplication<PatientApplication>(*args)
	println("\nHealth Care Spring Boot Project !!!!!")
}