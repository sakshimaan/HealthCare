package com.healthcare.patient.appointments.service

import com.healthcare.patient.appointments.model.Appointment

interface AppointmentService {
   fun getAll(personId : String): List<Appointment>
   fun create(appointment: Appointment): Appointment
   fun update(appointment: Appointment, id: String): Appointment
   fun deleteOne(id: String)
}