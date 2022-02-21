package com.healthcare.patient.persons.controller

import com.healthcare.patient.responseMessages.ApiResponseMessages.Companion.PERSON_ADDED
import com.healthcare.patient.responseMessages.ApiResponseMessages.Companion.PERSON_UPDATED
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
    fun getByName(
        @RequestParam("firstName") firstName : String?,
        @RequestParam("role") role:String?,
        @RequestParam("city") city:String?,
        @RequestParam("hospitalId") hospitalId:String?
    ): ResponseEntity<List<Person>>? {
        firstName?.let { return ResponseEntity.ok(personService.getByFirstName(firstName)) }
        role?.let { return ResponseEntity.ok(personService.getByRole(role)) }
        city?.let { return ResponseEntity.ok(personService.getByCity(city)) }
        hospitalId?.let { return ResponseEntity.ok(personService.getByHospitalId(hospitalId)) }
        return ResponseEntity.ok(personService.getAll())
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id:String):ResponseEntity<Person>{
            return ResponseEntity.ok(personService.getOne(id))
    }

    @PostMapping
    fun createPerson(@Valid @RequestBody person: Person):ResponseEntity<PersonRestResponse>{
            val message = PersonRestResponse(PERSON_ADDED, HttpStatus.CREATED,
                ZonedDateTime.now().toLocalDateTime(), personService.createPerson(person)
            )
            return ResponseEntity.ok(message)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id:String,@Valid @RequestBody person:Person):ResponseEntity<PersonRestResponse>{
            val message = PersonRestResponse(PERSON_UPDATED, HttpStatus.CREATED,
                ZonedDateTime.now().toLocalDateTime(), personService.updatePerson(id, person)
            )
            return ResponseEntity.ok(message)

    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable("id") id:String,@Valid @RequestBody person:Person):ResponseEntity<PersonRestResponse>{
        val message = PersonRestResponse(PERSON_UPDATED, HttpStatus.CREATED,
            ZonedDateTime.now().toLocalDateTime(),personService.partialUpdate(id,person))
        return ResponseEntity.ok(message)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id:String):ResponseEntity<Unit>{
        return ResponseEntity.ok(personService.deleteOne(id))
    }
}