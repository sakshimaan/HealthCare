package com.healthcare.patient.persons.service

import com.healthcare.patient.persons.model.Person

interface PersonService {
    fun createPerson(person: Person): Person
    fun getAll(): List<Person>
    fun getOne(id: String): Person

    fun getByFirstName(firstName: String): List<Person>
    fun getByRole(role: String): List<Person>
    fun getByCity(city: String): List<Person>
    fun getByHospitalId(hospitalId: String): List<Person>

    fun updatePerson(id: String, person: Person): Person
    fun partialUpdate(id: String, person: Person): Person
    fun deleteOne(id: String)
}
