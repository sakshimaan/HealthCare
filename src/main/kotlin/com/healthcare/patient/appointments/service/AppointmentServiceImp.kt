package com.healthcare.patient.appointments.service

import com.healthcare.patient.appointments.model.Appointment
import com.healthcare.patient.appointments.repository.AppointmentRepository
import com.healthcare.patient.exceptionHandling.CustomException
import com.healthcare.patient.persons.model.Person
import com.healthcare.patient.persons.model.Role
import com.healthcare.patient.persons.repository.PersonRepository
import com.healthcare.patient.responseMessages.FailureMessages.Companion.APPOINTMENT_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.APPOINTMENT_ID_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.PERSON_ID_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.PRACTITIONER_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.SLOT_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.UNIQUE_ID_ERROR
import org.apache.commons.lang3.RandomStringUtils
import org.springframework.stereotype.Service

@Service
class AppointmentServiceImp(private val repository:AppointmentRepository,private val personRepository: PersonRepository) : AppointmentService{

    override fun getAll(personId:String): List<Appointment>{
        try {
            val person:Person = personRepository.findById(personId).orElseThrow{ CustomException(PERSON_ID_ERROR)}
            if(person.role != Role.PATIENT && person.role != Role.PHYSICIAN) {
                throw CustomException(APPOINTMENT_ERROR)
            }
            return repository.findAll()
        }catch (e: Exception) {
            throw CustomException(APPOINTMENT_ERROR)
        }
    }

    override fun create(appointment: Appointment): Appointment {
        appointment.id = generateUniqueId()
        try{
            if(repository.existsByStartDate(appointment.startDate)){
                throw CustomException(SLOT_ERROR)
            }
        } catch(e:Exception){
        throw CustomException(SLOT_ERROR)
    }
        try{
            val person:Person = personRepository.findById(appointment.practitionerId).orElseThrow { Exception("Practitioner not found")}
            if(!person.active){
                throw CustomException(PRACTITIONER_ERROR)
            }
            return repository.save(appointment)
        }catch (e:Exception){
            throw CustomException(PRACTITIONER_ERROR)
        }
    }

    override fun update(appointment: Appointment, id: String): Appointment {
        val oldId = repository.findById(id).orElseThrow { Exception(APPOINTMENT_ID_ERROR) }
        oldId.patientId = appointment.patientId
        oldId.practitionerId = appointment.practitionerId
        oldId.hospitalId = appointment.hospitalId
        oldId.startDate = appointment.startDate
        oldId.endDate = appointment.endDate
        oldId.createdAt = appointment.createdAt
        oldId.status = oldId.status
        return  repository.save(appointment)
    }

    override fun partialUpdate(appointment: Appointment, id: String): Appointment {
        val oldId = repository.findById(id).orElseThrow { Exception(APPOINTMENT_ID_ERROR) }
        appointment.patientId?.let { oldId.patientId = it }
        appointment.practitionerId?.let { oldId.practitionerId = it }
        appointment.hospitalId?.let { {oldId.hospitalId = it} }
        appointment.startDate?.let { oldId.startDate = it }
        appointment.endDate?.let { oldId.endDate = it }
        appointment.createdAt?.let { oldId.createdAt = it }
        appointment.status?.let { oldId.status = it }
        return repository.save(appointment)
    }

    override fun deleteOne(id: String) {
        return repository.deleteById(id)
    }

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