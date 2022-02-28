package com.healthcare.patient.appointments.controller

import com.healthcare.patient.appointments.model.Appointment
import com.healthcare.patient.appointments.model.Status
import com.healthcare.patient.appointments.service.AppointmentService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.security.Principal
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(AppointmentController::class)
internal class AppointmentControllerTest {

    @MockkBean
    lateinit var mockAppointmentService: AppointmentService

    @Autowired
    private lateinit var mockMvc: MockMvc
    private val personId = "36283007"
    private val mockPrincipal = mockk<Principal> {
        every { name } returns personId
    }

    @Test
    fun getRequest_ReturnAppointmentWithStatus200() {
        val expectedData = Appointment(
            "39921555", "36283007", "32840339", "7650114", LocalDateTime.now(),
            LocalDateTime.now(), Status.BOOKED
        )
        every { mockAppointmentService.getAll("36283007") } returns listOf(expectedData)
        performMeForGetRequest().andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
        verify(exactly = 1) {
            mockAppointmentService.getAll("36283007")
        }
        assertEquals(mockAppointmentService.getAll("36283007"), listOf(expectedData))
    }

    private fun performMeForGetRequest() =
        mockMvc.perform(
            get("http://localhost:8080/appointment/personId?personId=36283007")
                .principal(mockPrincipal)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )

    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
    private val dateTime: LocalDateTime = LocalDateTime.parse("12-07-2024 05:30:44", formatter)

    // @Test
    fun postRequest_ReturnStatus200() {
        val data = Appointment(
            "39921555", "36283007", "32840339", "7650114", dateTime, dateTime, Status.BOOKED
        )
        every { mockAppointmentService.create(data) } returns data
        performMeForPostRequest().andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andReturn()
        verify(exactly = 1) {
            mockAppointmentService.create(data)
        }
        assertEquals(mockAppointmentService.create(data), data)
    }

    private fun performMeForPostRequest() =
        mockMvc.perform(
            post("http://localhost:8080/appointment")
                .principal(mockPrincipal)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
        )
}
