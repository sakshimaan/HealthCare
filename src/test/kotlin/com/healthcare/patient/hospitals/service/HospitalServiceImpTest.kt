package com.healthcare.patient.hospitals.service

import com.healthcare.patient.hospitals.model.Hospital
import com.healthcare.patient.hospitals.repository.HospitalRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class HospitalServiceImpTest{
    private val repository : HospitalRepository = mockk()
    private val hospitalService = HospitalServiceImp(repository)
    private val hospital : List<Hospital> = mockk()

    @Test
    fun whenGetAllHospital_thenReturnHospital(){
        every { repository.findAll() } returns hospital
        val result = hospitalService.getAll()
        verify(exactly = 1){
            repository.findAll()
        }
        assertEquals(result,hospital)
    }

    @Test
    fun whenGetByNameHospital_thenReturnHospital(){
        every { repository.findByName("Fortis") } returns hospital
        val result = hospitalService.getByName("Fortis")
        verify(exactly = 1){
            repository.findByName("Fortis")
        }
        assertEquals(result,hospital)
    }

    @Test
    fun whenGetByCityHospital_thenReturnHospital(){
        every { repository.findByCity("Noida") } returns hospital
        val result = hospitalService.getByCity("Noida")
        verify(exactly = 1){
            repository.findByCity("Noida")
        }
        assertEquals(result,hospital)
    }

    @Test
    fun whenGetByRegistrationHospital_thenReturnHospital(){
        every { repository.findByRegistrationNo("H1") } returns hospital
        val result = hospitalService.getByRegistrationNo("H1")
        verify(exactly = 1){
            repository.findByRegistrationNo("H1")
        }
        assertEquals(result,hospital)
    }

    @Test
    fun whenDeleteHospital_thenReturnUnit(){
        every { repository.deleteById("1") } returns Unit
        val result = hospitalService.deleteOne("1")
        verify(exactly = 1){
            repository.deleteById("1")
        }
        assertEquals(result,Unit)
    }
}
