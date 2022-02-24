package com.healthcare.patient.persons.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.healthcare.patient.hospitals.model.Address
import com.healthcare.patient.responseMessages.FailureMessages.Companion.EMAIL_ID_ERROR
import com.healthcare.patient.responseMessages.FailureMessages.Companion.INCORRECT_DOB
import com.healthcare.patient.responseMessages.FailureMessages.Companion.INCORRECT_GENDER
import com.healthcare.patient.responseMessages.FailureMessages.Companion.INVALID_PHONE_NO
import com.healthcare.patient.responseMessages.FailureMessages.Companion.NAME_ERROR
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate
import java.time.LocalDateTime
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Past
import javax.validation.constraints.Pattern

@Document("persons")
data class Person(
    @Id
    var id: String?,
    @field:NotEmpty(message = NAME_ERROR)
    var firstName: String,
    var lastName: String?,
    @field:Email(message = EMAIL_ID_ERROR)
    var email: String,
    @field:Past(message = INCORRECT_DOB)
    @JsonFormat(pattern = "dd-MM-yyyy")
    var dob: LocalDate?,
    @field:Pattern(regexp = "^[6-9][0-9]{9}\$", message = INVALID_PHONE_NO)
    var phoneNo: String,
    @field:Pattern(regexp = "^(male)?(MALE)?(female)?(FEMALE)?\$", message = INCORRECT_GENDER)
    var gender: String?,
    var address: Address,
    var role: Role,
    var active: Boolean,
    var hospitalId: List<String>?,

    @JsonIgnore
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    @JsonIgnore
    var createdAt: LocalDateTime = LocalDateTime.now()
)
