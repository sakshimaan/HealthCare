package com.healthcare.patient.persons.controller

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.healthcare.patient.hospitals.model.Address
import com.healthcare.patient.persons.model.Person
import com.healthcare.patient.persons.model.Role
import com.healthcare.patient.persons.service.PersonService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(PersonController::class)
internal class PersonControllerTest {

    @MockkBean
    lateinit var mockPersonService : PersonService
    @Autowired
    lateinit var mockMvc: MockMvc

    private val mapper = jacksonObjectMapper()

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
    private val dateTime: LocalDate = LocalDate.parse("12-07-1999", formatter)

    private val person = Person(
        "36283007", "Sakshi", "Maan", "sakshi@gmail.com", dateTime,
        "9988776655", "female", Address("2", "D", "New Delhi", "Delhi", "India", 110025),
        Role.PATIENT, false, "37650114".lines()
    )

  //  @Test
    fun postRequest_thenReturnStatus200() {
        every { mockPersonService.createPerson(person) } returns person
        mockMvc.perform(
            post("/person")
                .content(mapper.writeValueAsString(person))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.firstName").value("Sakshi"))
    }
}
