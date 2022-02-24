package com.healthcare.patient.persons.service

import com.healthcare.patient.persons.model.Person
import com.healthcare.patient.persons.repository.PersonRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class PersonServiceImpTest {
    private val repository: PersonRepository = mockk()
    private val personService = PersonServiceImp(repository)
    private val person: List<Person> = mockk()

    @Test
    fun whenGetPersonByFirstName_thenReturnPerson() {
        every { repository.findByFirstName("Sakshi") } returns person
        val result = personService.getByFirstName("Sakshi")
        verify(exactly = 1) {
            repository.findByFirstName("Sakshi")
        }
        assertEquals(result, person)
    }
    @Test
    fun whenGetPersonByRole_thenReturnPerson() {
        every { repository.findByRole("PATIENT") } returns person
        val result = personService.getByRole("PATIENT")
        verify(exactly = 1) {
            repository.findByRole("PATIENT")
        }
        assertEquals(result, person)
    }

    @Test
    fun whenGetPersonByCity_thenReturnPerson() {
        every { repository.findByCity("Noida") } returns person
        val result = personService.getByCity("Noida")
        verify(exactly = 1) {
            repository.findByCity("Noida")
        }
        assertEquals(result, person)
    }

    @Test
    fun whenGetPersonByHospitalId_thenReturnPerson() {
        every { repository.findByHospitalId("37650114") } returns person
        val result = repository.findByHospitalId("37650114")
        verify(exactly = 1) {
            repository.findByHospitalId("37650114")
        }
        assertEquals(result, person)
    }
    @Test
    fun whenGetAllPerson_thenReturnPersonList() {
        every { repository.findAll() } returns person
        val result = personService.getAll()
        verify(exactly = 1) {
            repository.findAll()
        }
        assertEquals(result, person)
    }

    // @Test
    fun whenGetPersonById_thenReturnPerson() {
        val person: Person = mockk()
        every { repository.findById("36283007").get() } returns person
        val result = personService.getOne("36283007")
        verify(exactly = 1) {
            repository.findById("36283007")
        }
        assertEquals(person, result)
    }

    // @Test
    fun whenCreatePerson_thenReturnPerson() {
        val person: Person = mockk()
        every { repository.save(person) } returns person
        val result = personService.createPerson(person)
        verify(exactly = 1) {
            repository.save(person)
        }
        assertEquals(result, person)
    }
    // @Test
    fun whenUpdatePerson_theReturnPerson() {
        val person: Person = mockk()
        every { repository.save(person) } returns person
        val result = personService.updatePerson("36283007", person)
        verify(exactly = 1) {
            repository.save(person)
        }
        assertEquals(result, person)
    }

    @Test
    fun whenDeletePerson_thenReturnUnit() {
        every { repository.deleteById("1") } returns Unit
        val result = personService.deleteOne("1")
        verify(exactly = 1) {
            repository.deleteById("1")
        }
        assertEquals(result, Unit)
    }
}
