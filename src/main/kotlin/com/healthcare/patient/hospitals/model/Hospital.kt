package com.healthcare.patient.hospitals.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.healthcare.patient.responseMessages.FailureMessages.Companion.EMAIL_ID_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.HOSPITAL_NAME_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.INVALID_PHONE_NO
import com.healthcare.patient.responseMessages.FailureMessages.Companion.TELEPHONE_ERROR
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern

@Document("hospitals")
data class Hospital(
    @Id
    var id: String?,
    @field:NotEmpty(message = HOSPITAL_NAME_ERROR)
    var name: String,
    @field:Pattern(regexp = "^[0-9]{5}(-)?[0-9]{6}\$", message = TELEPHONE_ERROR)
    var telephoneNo: String,
    @field:Pattern(regexp = "^[6-9][0-9]{9}\$", message = INVALID_PHONE_NO)
    var emergencyContactNo: String,
    var registrationNo: String,
    @field:Email(message = EMAIL_ID_ERROR)
    var email: String,
    var address: Address,

    @JsonIgnore
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    @JsonIgnore
    var createdAt: LocalDateTime = LocalDateTime.now(),

)
