package com.healthcare.patient.hospitals.repository

import com.healthcare.patient.hospitals.model.Hospital
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param

interface HospitalRepository : MongoRepository<Hospital, String> {
    fun findByName(@Param("name") name: String): List<Hospital>

    @Query("{'Address.city':?0}")
    fun findByCity(@Param("city") city: String): List<Hospital>

    fun findByRegistrationNo(@Param("registrationNo")registrationNo: String): List<Hospital>
}
