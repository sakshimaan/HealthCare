package com.healthcare.patient.hospitals.controller

import com.healthcare.patient.hospitals.model.Hospital
import com.healthcare.patient.hospitals.service.HospitalService
import com.healthcare.patient.responseMessages.ApiResponseMessages.Companion.HOSPITAL_ADDED
import com.healthcare.patient.responseMessages.ApiResponseMessages.Companion.HOSPITAL_UPDATED
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.ZonedDateTime
import javax.validation.Valid

@RestController
@RequestMapping("/hospital")
class HospitalController(private val hospitalService: HospitalService) {

    @GetMapping
    fun getHospital(
        @RequestParam("name") name: String?,
        @RequestParam("city") city: String?,
        @RequestParam("registrationNo") registrationNo: String?
    ): ResponseEntity<List<Hospital>> {
        name?.let { return ResponseEntity.ok(hospitalService.getByName(name)) }
        city?.let { return ResponseEntity.ok(hospitalService.getByCity(city)) }
        registrationNo?.let { return ResponseEntity.ok(hospitalService.getByRegistrationNo(registrationNo)) }
        return ResponseEntity.ok(hospitalService.getAll())
    }

    @GetMapping("/{id}")
    fun getOne(@PathVariable("id") id: String): ResponseEntity<Hospital> {
        return ResponseEntity.ok(hospitalService.getOne(id))
    }

    @PostMapping
    fun createHospital(@Valid @RequestBody hospital: Hospital): ResponseEntity<HospitalRestResponse> {
        val message = HospitalRestResponse(
            HOSPITAL_ADDED, HttpStatus.CREATED.value(),
            ZonedDateTime.now().toLocalDateTime(), hospitalService.createHospital(hospital)
        )
        return ResponseEntity.ok(message)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable("id") id: String, @Valid @RequestBody hospital: Hospital): ResponseEntity<HospitalRestResponse> {
        val message = HospitalRestResponse(
            HOSPITAL_UPDATED, HttpStatus.CREATED.value(),
            ZonedDateTime.now().toLocalDateTime(), hospitalService.updateHospital(id, hospital)
        )
        return ResponseEntity.ok(message)
    }

    @PatchMapping("/{id}")
    fun partialUpdate(@PathVariable("id") id: String, @Valid @RequestBody hospital: Hospital): ResponseEntity<HospitalRestResponse> {
        val message = HospitalRestResponse(
            HOSPITAL_UPDATED, HttpStatus.CREATED.value(),
            ZonedDateTime.now().toLocalDateTime(), hospitalService.partialUpdateHospital(id, hospital)
        )
        return ResponseEntity.ok(message)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: String): ResponseEntity<Unit> {
        return ResponseEntity.ok(hospitalService.deleteOne(id))
    }
}
