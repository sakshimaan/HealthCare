package com.healthcare.patient.hospitals.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.healthcare.patient.persons.model.Person
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.validation.constraints.*

@Document("hospitals")
data class Hospital(
    @Id
    var id:String?,
    @field:NotEmpty(message= "Hospital name can not be empty")
    var name:String,
    @field:Pattern(regexp = "^[0-9]{5}(-)?[0-9]{6}\$", message = "Incorrect Telephone number")
    var telephoneNo:String,
    @field:Pattern(regexp = "^[6-9][0-9]{9}\$", message = "Incorrect emergency contact number")
    var emergencyContactNo:String,
    var registrationNo : String,
    @field:Email(message = "Incorrect Email Id")
    var email:String,
    var address:Address,

    @JsonIgnore
    var updatedAt: LocalDateTime = LocalDateTime.now(),
    @JsonIgnore
    var createdAt: LocalDateTime = LocalDateTime.now(),

)