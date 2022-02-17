package com.healthcare.patient.persons.controller

import com.fasterxml.jackson.annotation.JsonFormat
import com.healthcare.patient.persons.model.Person
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

class PersonRestResponse(
    var message:String,
    var status: HttpStatus,
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    var time:LocalDateTime,
    var data:Person
)
