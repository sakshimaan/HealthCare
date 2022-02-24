package com.healthcare.patient.exceptionHandling

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class ErrorResponse(
    var status: Int? = null,
    var message: String? = null,
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    var timeStamp: LocalDateTime? = null,
    var details: Map<String, String?>? = null
)
