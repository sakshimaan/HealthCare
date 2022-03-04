package com.healthcare.patient.appointments.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.healthcare.patient.responseMessages.FailureMessages.Companion.APPOINTMENT_DATE
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.validation.constraints.FutureOrPresent

@Document("appointments")
data class Appointment(
    @Id
    var id: String?,
    var patientId: String,
    var practitionerId: String,
    var hospitalId: String,

    @field:FutureOrPresent(message = APPOINTMENT_DATE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    var startDate: LocalDateTime,

    @field:FutureOrPresent(message = APPOINTMENT_DATE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    var endDate: LocalDateTime,

    var status: Status,
    @JsonIgnore
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    @JsonIgnore
    var createdAt: LocalDateTime = LocalDateTime.now()
)
