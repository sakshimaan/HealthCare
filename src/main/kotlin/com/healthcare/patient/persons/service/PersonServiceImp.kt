package com.healthcare.patient.persons.service

import com.healthcare.patient.exceptionHandling.ServiceException
import com.healthcare.patient.persons.model.Person
import com.healthcare.patient.persons.model.Role
import com.healthcare.patient.persons.repository.PersonRepository
import com.healthcare.patient.validator.Validate
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service

@Service
class PersonServiceImp(private val repository: PersonRepository):PersonService{

    override fun createPerson(person:Person):Person {
        person.id = generateUniqueId()
        try {
            if (person.role == Role.PATIENT && person.dob == null) throw ServiceException("Patient Birth Date is mandatory")
        }catch (e:ServiceException) {
            throw ServiceException("Patient Birth Date is mandatory")
        }
        try {
            if(person.address.city.isEmpty() ||person.address.state.isEmpty() || person.address.country.isEmpty())
                throw ServiceException("Improper address details")
        }catch (e:ServiceException){
            throw ServiceException("Improper address details")
        }
        try {
            if (!Validate.pinCodeValid(person.address.pinCode)) throw ServiceException("Incorrect Pin Code")
        }catch (e:Exception){
            throw ServiceException("Incorrect Pin Code")
        }

        try{
            return repository.save(person)
        }catch (e:Exception){
            throw ServiceException("Unable to add Person details")
        }
    }

    override fun getAll(): List<Person> {
        try{
            return repository.findAll()
        }catch (e:Exception){
            throw ServiceException("Something went wrong")
        }
    }

    override fun getOne(id: String): Person {
        return repository.findById(id).orElseThrow { Exception("Person with id:$id not found") }
    }

    override fun getByFirstName(firstName: String): List<Person> {
        try{
            return  repository.findByFirstName(firstName)
        }catch (e:Exception){
            throw ServiceException("Person with name:$firstName not found")
        }
    }

    override fun getByRole(role: String): List<Person> {
        try{
            return repository.findByRole(role)!!
        }catch (e:Exception){
            throw ServiceException("Person with role:$role not found")
        }
    }

    override fun getByCity(city: String): List<Person> {
        try{
            return repository.findByCity(city)!!
        }catch (e:Exception){
            throw ServiceException("Person with city name :$city not found")
        }
    }

    override fun getByHospitalId(hospitalId: String): List<Person> {
        try{
            return repository.findByHospitalId(hospitalId)!!
        }catch (e:Exception){
            throw ServiceException("No Person Associated with hospital id : $hospitalId")
        }
    }

    override fun updatePerson(id: String, person: Person): Person {
        val oldId = repository.findById(id).orElseThrow { Exception("Person with id:$id not found") }
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
        val oldId = repository.findById(id).orElseThrow { Exception("Person with id : $id not found") }
        person.firstName.let { oldId.firstName = it }
        person.lastName.let { oldId.lastName = it}
        person.email.let { oldId.email = it }
        person.dob.let { oldId.dob = it }
        person.phoneNo.let { oldId.phoneNo = it}
        person.gender.let { oldId.gender = it }
        person.address.houseNo.let { oldId.address.houseNo = it }
        person.address.block.let { oldId.address.block = it }
        person.address.city.let { oldId.address.city = it }
        person.address.state.let { oldId.address.state = it }
        person.address.country.let { oldId.address.country = it }
        person.address.pinCode.let { oldId.address.pinCode = it }
        person.role.let { oldId.role = it}
        person.active.let { oldId.active = it }
        return repository.save(person)
        }

    override fun deleteOne(id:String) {
        return repository.deleteById(id)
    }

    fun generateUniqueId(): String {
        var generatedId:String
        //val values = ('0'..'9')
        var attempt = 1
        do {
            generatedId = RandomStringUtils.randomNumeric(8)
           // generatedId = (values).map { it }.shuffled().subList(0, 5).joinToString("")
            if(++attempt > 10) {
                throw ServiceException("Unable to generate Unique Id")
            }
        }while(repository.existsById(generatedId))
        return  generatedId
    }
}