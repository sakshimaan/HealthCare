package com.healthcare.patient.persons.repository

import com.healthcare.patient.persons.model.Person
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param

interface PersonRepository:MongoRepository<Person,String> {
    fun findByRole(@Param("role") role: String?): List<Person>?

    @Query("{'Address.city':?0}")
    fun findByCity(@Param("city") city:String?) : List<Person>?
}