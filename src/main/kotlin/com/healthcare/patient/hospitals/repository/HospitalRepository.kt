package com.healthcare.patient.hospitals.repository

import com.healthcare.patient.hospitals.model.Hospital
import org.springframework.data.mongodb.repository.MongoRepository

interface HospitalRepository:MongoRepository<Hospital,String>