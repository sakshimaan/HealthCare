package com.healthcare.patient.persons.controller

import com.healthcare.patient.persons.model.Person
import com.healthcare.patient.persons.service.PersonService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/person")
class PersonController(private val personService: PersonService) {

    @GetMapping
    fun getAll():ResponseEntity<List<Person>>{
            return  ResponseEntity.ok(personService.getAll())
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id:String):ResponseEntity<Person>{
            return ResponseEntity.ok(personService.getOne(id))
    }

    @GetMapping("/role")
    fun getByRole(@RequestParam("role") role:String):ResponseEntity<List<Person>>?{
            return ResponseEntity.ok(personService.getByRole(role))
    }

    @GetMapping("/city")
    fun getByCity(@RequestParam("city") city:String):ResponseEntity<List<Person>>?{
            return ResponseEntity.ok(personService.getByCity(city))
    }

    @PostMapping
    fun createPerson(@Valid @RequestBody person: Person):ResponseEntity<PersonRestResponse>{
            val message = PersonRestResponse(
                "Person details added successfully", HttpStatus.CREATED,
                ZonedDateTime.now().toLocalDateTime(), personService.createPerson(person)
            )
            return ResponseEntity.ok(message)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id:String,@Valid @RequestBody person:Person):ResponseEntity<PersonRestResponse>{
            val message = PersonRestResponse(
                "Person details updated successfully", HttpStatus.CREATED,
                ZonedDateTime.now().toLocalDateTime(), personService.updatePerson(id, person)
            )
            return ResponseEntity.ok(message)

    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable("id") id:String,@Valid @RequestBody person:Person):ResponseEntity<PersonRestResponse>{
        val message = PersonRestResponse("Person details updated successfully", HttpStatus.CREATED,
            ZonedDateTime.now().toLocalDateTime(),personService.partialUpdate(id,person))
        return ResponseEntity.ok(message)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id:String):ResponseEntity<Unit>{
        return ResponseEntity.ok(personService.deleteOne(id))
    }
}