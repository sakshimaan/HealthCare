package com.healthcare.patient.persons.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.healthcare.patient.hospitals.model.Address
import com.healthcare.patient.persons.model.Person
import com.healthcare.patient.persons.model.Role
import com.healthcare.patient.persons.service.PersonService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate
import java.time.LocalDateTime

@WebMvcTest
internal class PersonControllerTest {

    @field:Autowired
    lateinit var mockMvc: MockMvc

    @field:MockkBean
    lateinit var personService: PersonService
    // private val service = PersonController(personService)
    private val mapper = jacksonObjectMapper()
    private val person = Person(
        "36283007", "Sakshi", "Maan", "sakshi@gmail.com", LocalDate.parse("16-09-1999"),
        "9988776655", "female", Address("2", "D", "New Delhi", "Delhi", "India", 110025),
        Role.PATIENT, false, "37650114".lines(), LocalDateTime.now(), LocalDateTime.now()
    )

    @Test
    fun postRequest_thenReturnStatus200() {
        every { personService.createPerson(person) } returns person
        mockMvc.perform(
            post("/person")
                .content(mapper.writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.firstName").value("Sakshi"))
    }
    // @Test
//    fun givenPersonExist_whenGetRequest_returnPersonDetailsWithStatus200(){
//        val person:List<Person> = mockk()
//        every { personService.getAll() } returns person
//        mockMvc.perform(get("/person"))
//            .andExpect(status().isOk)
//            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//            .andExpect(jsonPath("$.firstName").value("Sakshi"))
//
//    }
}
