package com.example.address_project.global.error

abstract class BusinessException (
    val errorCode : ErrorCode
) : RuntimeException()
