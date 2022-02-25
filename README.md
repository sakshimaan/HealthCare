# SPRINGBOOT - KOTLIN - MONGODB - CRUD API Project

A simple project to perform CRUD operations using SpringBoot, Kotlin, Mongodb, Gradle and Java 17 for **Patient HealthCare Application**.

IDE used for this project is :- Intellij IDEA 2020.3.2 (Community Edition)

This project was created using **spring initializer** https://start.spring.io/ with options selected :-
```
● Project – Gradle Project
● Language – Kotlin
● Spring Boot - 2.6.3
● Packaging – jar
● Java – 17
```

## Dependencies selected :-

```
● Spring Web
● Spring data Mongodb
● Validation
```
Dependencies can also be added later in the “build.gradle.kts” file.

I’ve added other required dependencies from maven repository https://mvnrepository.com/

- implementation("org.apache.commons:commons-lang3:3.12.0")
- implementation("org.springframework.boot:spring-boot-starter-validation:2.6.3")
- testImplementation("org.springframework.boot:spring-boot-starter-test")
- testImplementation("io.mockk:mockk:1.12.2")
- testImplementation("com.ninja-squad:springmockk:3.1.0")
- implementation("org.springframework.boot:spring-boot-starter-security:2.6.3")

# List of APIs

This project consists of APIs to (Get, Add, Update, Partially Update, Delete) Patient,
Practitioner and Hospital details.

**For Person:-**

I’ve used Postman https://www.postman.com/ for checking endpoint responses.

Get endpoint for getting person details consists of options :- 
- get all details
- get details by first name
- get details by city
- get details by role
- get details by Hospital id

GET :- [http://localhost:8080/person](http://localhost:8080/person)

POST :- [http://localhost:8080/person](http://localhost:8080/person)

PUT :- [http://localhost:8080/person/](http://localhost:8080/person/)

PATCH :- [http://localhost:8080/person/](http://localhost:8080/person/)

DELETE :- [http://localhost:8080/person/](http://localhost:8080/person/)


**For Hospital :-**

Get endpoint of hospital consists of options :- 
- get all details
- get by name
- get by city
- get by registration no

GET :- [http://localhost:8080/hospital](http://localhost:8080/hospital)

POST :- [http://localhost:8080/hospital](http://localhost:8080/hospital)

PUT :- [http://localhost:8080/hospital/](http://localhost:8080/hospital/)

PATCH :- [http://localhost:8080/hospital/](http://localhost:8080/hospital/)

DELETE :- [http://localhost:8080/hospital/](http://localhost:8080/hospital/)


**For Appointment :-**

GET :- [http://localhost:8080/appointment/personId?personId=36283007](http://localhost:8080/appointment/personId?personId=36283007)

POST :- [http://localhost:8080/appointment](http://localhost:8080/appointment)

PUT :- [http://localhost:8080/appointment/](http://localhost:8080/appointment/)

PATCH :- [http://localhost:8080/appointment/](http://localhost:8080/appointment/)

DELETE :- [http://localhost:8080/appointment/](http://localhost:8080/appointment/)

# Features :-

- Every person has roles like - PHYSICIAN, THERAPIST, DENTIST, PHARMACIST,
    NURSE, DIETITIAN, PATIENT.
- Persons have their Status ( “is active” or “not active”).
- A physician can be associated with many hospitals.
- Patients can select available appointments with the practitioner.
- Appointment status can be BOOKED, COMPLETED, CANCELED.
- Only Patient and Physician can see all the appointment details.


