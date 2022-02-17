package com.healthcare.patient.hospitals.service

import com.healthcare.patient.hospitals.model.Hospital

interface HospitalService {
    fun getAll(): List<Hospital>
    fun getOne(id:String): Hospital
    fun createHospital(hospital: Hospital): Hospital
    fun updateHospital(id: String, hospital: Hospital): Hospital
    fun partialUpdateHospital(id: String, hospital: Hospital): Hospital
    fun deleteOne(id: String)
}