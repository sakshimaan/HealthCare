package com.healthcare.patient.responseMessages

class FailureMessages {
    companion object{
//        !!!-----Person Service Layer Messages-----!!!
        const val DOB_ERROR = "Patient Birth Date is mandatory"
        const val ADDRESS_ERROR = "Improper address details"
        const val PIN_CODE_ERROR = "Incorrect Pin code, Please provide a valid value"
        const val PERSON_CREATE_ERROR = "Unable to add Person details"
        const val PERSON_ID_ERROR = "No person exists with given id,Please register yourself"

        const val UNIQUE_ID_ERROR = "Unable to generate Unique Id"
        const val INTERNAL_SERVER_ERROR = "Something went wrong at server side"


//      !!!----Person Field Error Messages----!!!
        const val NAME_ERROR = "First name can not be blank"
        const val EMAIL_ID_ERROR = "Please enter proper syntax of email id"
        const val INCORRECT_DOB = "Date of Birth can not be future or present date."
        const val INVALID_PHONE_NO = "Phone Number should have 10 digits  and starting 3 digits can be in between 6-9 only."

//      !!!-----Hospital Service Layer Messages-----!!!
        const val HOSPITAL_ID_ERROR = "No hospital exists with this id"

//      !!!---Hospital Field Error Messages----!!!
        const val HOSPITAL_NAME_ERROR = "Hospital name can not be blank"
        const val TELEPHONE_ERROR = "Telephone number should be in format :- XXXXX-XXXXXX"

//      !!!---Appointment Service Layer Messages-----!!!
        const val APPOINTMENT_ERROR = "Only Patient and Physician can see appointment details"
        const val SLOT_ERROR = "Please select available slot"
        const val PRACTITIONER_ERROR = "Practitioner currently unavailable"
        const val APPOINTMENT_ID_ERROR = "Appointment with id not found"
        const val APPOINTMENT_DATE = "Appointment date can not in Past"

    }
}