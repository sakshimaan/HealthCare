package com.healthcare.patient.hospitals.model

data class Address(
    var houseNo: String,
    var block: String,
    var city: String,
    var state: String,
    var country: String,
    var pinCode: Int
)
