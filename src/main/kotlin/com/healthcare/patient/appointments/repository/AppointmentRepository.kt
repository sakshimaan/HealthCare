package com.healthcare.patient.appointments.repository

import com.healthcare.patient.appointments.model.Appointment
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime

interface AppointmentRepository:MongoRepository<Appointment,String>{

     fun existsByStartDate(startDate: LocalDateTime):Boolean

}
