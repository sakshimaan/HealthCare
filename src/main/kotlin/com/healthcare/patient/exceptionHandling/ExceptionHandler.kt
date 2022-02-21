package com.healthcare.patient.exceptionHandling

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@ControllerAdvice
class ExceptionHandler{

    @ExceptionHandler(CustomException::class)
    fun serviceExe(ex:CustomException, request: WebRequest):ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message, LocalDateTime.now())
        return ResponseEntity(error, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(Exception::class)
    fun globalException(ex:Exception,request:WebRequest): ResponseEntity<ErrorResponse> {
        val error = ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.message,LocalDateTime.now())
        return ResponseEntity(error,HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun noSuchElement(ex:NoSuchElementException,request: WebRequest):ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(HttpStatus.BAD_REQUEST.value(),ex.message, LocalDateTime.now())
        return ResponseEntity(error,HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgument(ex:MethodArgumentNotValidException,request: WebRequest):ResponseEntity<ErrorResponse>{
        val fieldErrors: List<FieldError> = ex.fieldErrors
        ex.bindingResult.fieldErrors
        val errorMapping = fieldErrors.associate { it.field to it.defaultMessage }
        val error = ErrorResponse(HttpStatus.BAD_REQUEST.value(),"Validation Field Error", LocalDateTime.now(),errorMapping)
        return  ResponseEntity(error,HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun illegal(ex:IllegalArgumentException,request: WebRequest):ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message, LocalDateTime.now())
        return ResponseEntity(error,HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun httpMessage(ex:HttpMessageNotReadableException,request: WebRequest):ResponseEntity<ErrorResponse>{
        val error = ErrorResponse(HttpStatus.BAD_REQUEST.value(),"Invalid Json Format", LocalDateTime.now())
        return ResponseEntity(error,HttpStatus.BAD_REQUEST)
    }
}