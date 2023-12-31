package com.example.address_project.global.error

import com.example.address_project.global.error.exception.BusinessException
import com.example.address_project.global.error.exception.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException::class)
    fun handlingBusinessException(e: BusinessException): ResponseEntity<ErrorResponse> {
        val code = e.errorCode
        val errorResponse = ErrorResponse(code.status, code.message, LocalDateTime.now()) // ErrorResponse 객체 생성
        return ResponseEntity(errorResponse, HttpStatus.valueOf(code.status)) // 생성한 객체 반환
    }
}