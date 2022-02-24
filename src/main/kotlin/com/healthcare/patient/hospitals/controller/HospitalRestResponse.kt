package com.healthcare.patient.hospitals.controller

import com.fasterxml.jackson.annotation.JsonFormat
import com.healthcare.patient.hospitals.model.Hospital
import java.time.LocalDateTime

class HospitalRestResponse(
    var message: String,
    var status: Int? = null,
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    var time: LocalDateTime,
    var data: Hospital
)
