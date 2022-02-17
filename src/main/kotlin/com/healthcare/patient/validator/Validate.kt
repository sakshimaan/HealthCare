package com.healthcare.patient.validator

class Validate {
    companion object{
        fun pinCodeValid(pinCode:Int):Boolean{
            val pattern = ("^[1-9][0-9]{5}\$")
            return pattern.toRegex().matches(pinCode.toString())
        }
    }
}