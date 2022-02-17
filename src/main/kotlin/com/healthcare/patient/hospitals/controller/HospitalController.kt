package com.healthcare.patient.hospitals.controller

import com.healthcare.patient.hospitals.model.Hospital
import com.healthcare.patient.hospitals.service.HospitalService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/hospital")
class HospitalController(private val hospitalService: HospitalService) {

    @GetMapping
    fun getHospital():ResponseEntity<List<Hospital>> {
            return ResponseEntity.ok(hospitalService.getAll())
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id:String): ResponseEntity<Hospital>{
            return ResponseEntity.ok(hospitalService.getOne(id))
    }

    @PostMapping
    fun createHospital(@Valid @RequestBody hospital:Hospital):ResponseEntity<HospitalRestResponse>{
            val message = HospitalRestResponse(
                "Hospital details added successfully", HttpStatus.CREATED,
                ZonedDateTime.now().toLocalDateTime(), hospitalService.createHospital(hospital)
            )
            return ResponseEntity.ok(message)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id:String,@Valid @RequestBody hospital: Hospital):ResponseEntity<HospitalRestResponse>{
            val message = HospitalRestResponse(
                "Hospital details updated successfully",HttpStatus.CREATED,
                ZonedDateTime.now().toLocalDateTime(), hospitalService.updateHospital(id, hospital)
            )
            return ResponseEntity.ok(message)
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable("id") id:String,@Valid @RequestBody hospital: Hospital):ResponseEntity<HospitalRestResponse>{
            val message = HospitalRestResponse(
                "Hospital details updated successfully",HttpStatus.CREATED,
                ZonedDateTime.now().toLocalDateTime(), hospitalService.partialUpdateHospital(id, hospital)
            )
            return ResponseEntity.ok(message)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id:String):ResponseEntity<Unit>{
        return ResponseEntity.ok(hospitalService.deleteOne(id))
    }
}