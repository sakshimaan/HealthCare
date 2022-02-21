package com.healthcare.patient.persons.service

import com.healthcare.patient.exceptionHandling.CustomException
import com.healthcare.patient.persons.model.Person
import com.healthcare.patient.persons.model.Role
import com.healthcare.patient.persons.repository.PersonRepository
import com.healthcare.patient.responseMessages.FailureMessages.Companion.ADDRESS_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.DOB_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.INTERNAL_SERVER_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.PERSON_CREATE_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.PERSON_ID_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.PIN_CODE_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.UNIQUE_ID_ERROR
import com.healthcare.patient.validator.Validate
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service

@Service
class PersonServiceImp(private val repository: PersonRepository):PersonService{

    override fun createPerson(person:Person):Person {
        person.id = generateUniqueId()
        try {
            if (person.role == Role.PATIENT && person.dob == null) throw CustomException(DOB_ERROR)
        }catch (e:CustomException) {
            throw CustomException(DOB_ERROR)
        }
        try {
            if(person.address.city.isEmpty() ||person.address.state.isEmpty() || person.address.country.isEmpty())
                throw CustomException(ADDRESS_ERROR)
        }catch (e:CustomException){
            throw CustomException(ADDRESS_ERROR)
        }
        try {
            if (!Validate.pinCodeValid(person.address.pinCode)) throw CustomException(PIN_CODE_ERROR)
        }catch (e:Exception){
            throw CustomException(PIN_CODE_ERROR)
        }

        try{
            return repository.save(person)
        }catch (e:Exception){
            throw CustomException(PERSON_CREATE_ERROR)
        }
    }

    override fun getAll(): List<Person> {
        try{
            return repository.findAll()
        }catch (e:Exception){
            throw CustomException(INTERNAL_SERVER_ERROR)
        }
    }

    override fun getOne(id: String): Person {
        return repository.findById(id).orElseThrow { Exception(PERSON_ID_ERROR) }
    }

    override fun getByFirstName(firstName: String): List<Person> = repository.findByFirstName(firstName)

    override fun getByRole(role: String): List<Person> = repository.findByRole(role)!!

    override fun getByCity(city: String): List<Person> = repository.findByCity(city)!!

    override fun getByHospitalId(hospitalId: String): List<Person> = repository.findByHospitalId(hospitalId)!!

    override fun updatePerson(id: String, person: Person): Person {
        val oldId = repository.findById(id).orElseThrow { Exception(PERSON_ID_ERROR) }
        oldId.firstName = person.firstName
        oldId.lastName = person.lastName
        oldId.email = person.email
        oldId.dob = person.dob
        oldId.phoneNo = person.phoneNo
        oldId.gender = person.gender
        oldId.address.houseNo = person.address.houseNo
        oldId.address.block = person.address.block
        oldId.address.city = person.address.city
        oldId.address.state = person.address.state
        oldId.address.country = person.address.country
        oldId.address.pinCode = person.address.pinCode
        oldId.role = person.role
        oldId.active = person.active
        oldId.hospitalId = person.hospitalId
        return repository.save(person)
    }

    override fun partialUpdate(id: String, person: Person): Person {
        val oldId = repository.findById(id).orElseThrow { Exception(PERSON_ID_ERROR) }
        person.firstName?.let { oldId.firstName = it }
        person.lastName?.let { oldId.lastName = it}
        person.email?.let { oldId.email = it }
        person.dob?.let { oldId.dob = it }
        person.phoneNo?.let { oldId.phoneNo = it}
        person.gender?.let { oldId.gender = it }
        person.address.houseNo?.let { oldId.address.houseNo = it }
        person.address.block?.let { oldId.address.block = it }
        person.address.city?.let { oldId.address.city = it }
        person.address.state?.let { oldId.address.state = it }
        person.address.country?.let { oldId.address.country = it }
        person.address.pinCode?.let { oldId.address.pinCode = it }
        person.role?.let { oldId.role = it}
        person.active?.let { oldId.active = it }
        return repository.save(person)
        }

    override fun deleteOne(id:String) = repository.deleteById(id)

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