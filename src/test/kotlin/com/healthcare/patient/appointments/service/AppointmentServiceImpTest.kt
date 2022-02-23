package com.healthcare.patient.appointments.service

import com.healthcare.patient.appointments.model.Appointment
import com.healthcare.patient.appointments.repository.AppointmentRepository
import com.healthcare.patient.persons.repository.PersonRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class AppointmentServiceImpTest{
    private val repository : AppointmentRepository = mockk()
    private val personRepository:PersonRepository = mockk()
    private val appointmentService = AppointmentServiceImp(repository,personRepository)
    private val appointment:List<Appointment> = mockk()

    @Test
    fun whenDeleteAppointment_thenReturnNull(){
        every { repository.deleteById("1") } returns Unit
        val result = appointmentService.deleteOne("1")
        verify(exactly = 1){
            repository.deleteById("1")
        }
        assertEquals(result,Unit)
    }
}