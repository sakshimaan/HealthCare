package com.healthcare.patient.appointments.controller

import com.fasterxml.jackson.annotation.JsonFormat
import com.healthcare.patient.appointments.model.Appointment
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class AppointmentRestResponse(
    var message:String,
    var status: HttpStatus,
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    var time: LocalDateTime,
    var data: Appointment
)