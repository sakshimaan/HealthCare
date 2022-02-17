package com.healthcare.patient.appointments.service

import com.healthcare.patient.appointments.model.Appointment
import com.healthcare.patient.appointments.repository.AppointmentRepository
import com.healthcare.patient.exceptionHandling.ServiceException
import com.healthcare.patient.persons.model.Person
import com.healthcare.patient.persons.model.Role
import com.healthcare.patient.persons.repository.PersonRepository
import org.springframework.stereotype.Service

@Service
class AppointmentServiceImp(private val repository:AppointmentRepository,private val personRepository: PersonRepository) : AppointmentService{

    override fun getAll(personId:String): List<Appointment>{
        try {
            val person:Person = personRepository.findById(personId).orElseThrow{ ServiceException("No person exists with id :$personId, Please register yourself")}
            if(person.role != Role.PATIENT && person.role != Role.PHYSICIAN) throw ServiceException("Only Patient and Physician can see appointment details")
            return repository.findAll()
        }catch (e: Exception) {
            throw ServiceException("Only Patient and Physician can see appointment details")
        }
    }

    override fun create(appointment: Appointment): Appointment {
        appointment.id = generateUniqueId()
        try{
            if(repository.existsByStartDate(appointment.startDate)){
                throw ServiceException("This slot is not available")
            }
        } catch(e:Exception){
        throw ServiceException("Please select available slot")
    }
        try{
            val person:Person = personRepository.findById(appointment.practitionerId).orElseThrow { Exception("Practitioner not found")}
            if(!person.active){
                throw ServiceException("Practitioner currently unavailable")
            }
            return repository.save(appointment)
        }catch (e:Exception){
            throw ServiceException("Practitioner currently unavailable")
        }
    }

    override fun update(appointment: Appointment, id: String): Appointment {
        val oldId = repository.findById(id).orElseThrow { Exception("Appointment with id: $id not found.") }
        oldId.patientId = appointment.patientId
        oldId.practitionerId = appointment.practitionerId
        oldId.hospitalId = appointment.hospitalId
        oldId.startDate = appointment.startDate
        oldId.endDate = appointment.endDate
        oldId.createdAt = appointment.createdAt
        oldId.status = oldId.status
        return  repository.save(appointment)
    }

    override fun deleteOne(id: String) {
        return repository.deleteById(id)
    }

    fun generateUniqueId(): String {
        var generatedId:String
        val values = ('A'..'Z') + ('a'..'z') + ('0'..'9')
        var attempt = 1
        do {
            generatedId = (values).map { it }.shuffled().subList(0, 10).joinToString("")
            if(++attempt > 10) {
                throw ServiceException("Unable to generate Unique Id")
            }
        }while(repository.existsById(generatedId))
        return  generatedId
    }
}