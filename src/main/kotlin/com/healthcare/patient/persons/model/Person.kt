package com.healthcare.patient.persons.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import com.healthcare.patient.hospitals.model.Address
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
    var id:String?,
    @field:NotEmpty(message = "First name can not be blank")
    var firstName:String,
    var lastName:String?,
    @field:Email(message = "Incorrect Email ID")
    var email:String,
    @field:Past(message = "Date of Birth can not be future or present date.")
    @JsonFormat(pattern = "dd-MM-yyyy")
    var dob:LocalDate?,

    @field:Pattern(regexp = "^[6-9][0-9]{9}\$", message = "Phone Number is invalid")
    var phoneNo:String,

    var gender:String?,
    var address: Address,
    var role:Role,
    var active:Boolean,
    var hospitalId:List<String>?,

    @JsonIgnore
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    @JsonIgnore
    var createdAt: LocalDateTime = LocalDateTime.now()
)