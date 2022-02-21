package com.healthcare.patient.hospitals.service

import com.healthcare.patient.exceptionHandling.CustomException
import com.healthcare.patient.hospitals.model.Hospital
import com.healthcare.patient.hospitals.repository.HospitalRepository
import com.healthcare.patient.responseMessages.FailureMessages.Companion.HOSPITAL_ID_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.INTERNAL_SERVER_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.UNIQUE_ID_ERROR
import com.healthcare.patient.validator.Validate
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service

@Service
class HospitalServiceImp(private val repository: HospitalRepository):HospitalService {

    override fun createHospital(hospital: Hospital): Hospital {
        hospital.id = generateUniqueId()
        try {
            if(hospital.address.city.isEmpty() || hospital.address.state.isEmpty() || hospital.address.country.isEmpty())
                throw CustomException("Improper address details")
        }catch (e:CustomException){
            throw CustomException("Improper address details")
        }
        try {
            if (!Validate.pinCodeValid(hospital.address.pinCode)) throw CustomException("Incorrect Pin Code")
        }catch (e:Exception){
            throw CustomException("Incorrect Pin Code")
        }
          try{
              return repository.save(hospital)
        }catch (e:Exception){
            throw Exception("Unable to add Hospital details")
        }
    }

    override fun getAll(): List<Hospital> {
       try{
           return repository.findAll()
       }catch (e:Exception){
           throw CustomException(INTERNAL_SERVER_ERROR)
       }
    }

    override fun getByName(name: String): List<Hospital>  = repository.findByName(name)

    override fun getByCity(city: String): List<Hospital> = repository.findByCity(city)

    override fun getByRegistrationNo(registrationNo: String): List<Hospital> = repository.findByRegistrationNo(registrationNo)

    override fun getOne(id: String): Hospital {
        return repository.findById(id).orElseThrow { Exception("Hospital with id:$id not found") }
    }


    override fun updateHospital(id: String, hospital: Hospital): Hospital {
        val oldId = repository.findById(id).orElseThrow { Exception("No hospital exist with id : $id") }
        oldId.name = hospital.name
        oldId.telephoneNo = hospital.telephoneNo
        oldId.emergencyContactNo = hospital.emergencyContactNo
        oldId.registrationNo = hospital.registrationNo
        oldId.email = hospital.email
        oldId.address.houseNo = hospital.address.houseNo
        oldId.address.block = hospital.address.block
        oldId.address.city = hospital.address.city
        oldId.address.state = hospital.address.state
        oldId.address.country = hospital.address.country
        oldId.address.pinCode = hospital.address.pinCode
        return repository.save(hospital)
    }

    override fun partialUpdateHospital(id: String, hospital: Hospital): Hospital {
        val oldId = repository.findById(id).orElseThrow { Exception(HOSPITAL_ID_ERROR) }
        hospital.name?.let { oldId.name = it}
        hospital.telephoneNo?.let { oldId.name = it}
        hospital.emergencyContactNo?.let { oldId.emergencyContactNo = it }
        hospital.registrationNo?.let { oldId.registrationNo = it }
        hospital.email?.let { oldId.email = it}
        hospital.address.houseNo?.let { oldId.address.houseNo = it}
        hospital.address.block?.let { oldId.address.block = it }
        hospital.address.city?.let {oldId.address.city = it }
        hospital.address.state?.let {oldId.address.state = it }
        hospital.address.country?.let { oldId.address.country= it}
        hospital.address.pinCode?.let { oldId.address.pinCode = it }
        return repository.save(hospital)
    }

    override fun deleteOne(id: String) = repository.deleteById(id)

    fun generateUniqueId(): String {
        var generatedId:String
        var attempt = 1
        do {
            generatedId = RandomStringUtils.randomNumeric(8)
            if(++attempt > 10) {
                throw CustomException(UNIQUE_ID_ERROR)
            }
        }while(repository.existsById(generatedId))
        return  generatedId
    }
}