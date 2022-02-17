package com.healthcare.patient.appointments.controller

import com.healthcare.patient.appointments.model.Appointment
import com.healthcare.patient.appointments.service.AppointmentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/appointment")
class AppointmentController(private var appointmentService: AppointmentService){

    @GetMapping("/personId")
    fun getAll(@RequestParam("personId") personId:String):ResponseEntity<List<Appointment>>{
            return ResponseEntity.ok(appointmentService.getAll(personId))
        }

    @PostMapping
    fun create(@Valid @RequestBody appointment: Appointment):ResponseEntity<AppointmentRestResponse>{
            val message = AppointmentRestResponse(
                "Appointment details added successfully", HttpStatus.CREATED,
                ZonedDateTime.now().toLocalDateTime(), appointmentService.create(appointment)
            )
            return ResponseEntity.ok(message)
    }

    @PutMapping("/{id}")
    fun update(@Valid @RequestBody appointment: Appointment, @PathVariable("id") id:String):ResponseEntity<AppointmentRestResponse>{
            val message = AppointmentRestResponse(
                "Appointment details updated successfully",HttpStatus.CREATED,
                ZonedDateTime.now().toLocalDateTime(), appointmentService.update(appointment, id)
            )
            return ResponseEntity.ok(message)
    }

    @DeleteMapping("/{id}")
    fun deleteOne(@PathVariable("id") id:String):ResponseEntity<Unit>{
        return ResponseEntity.ok(appointmentService.deleteOne(id))
    }
}