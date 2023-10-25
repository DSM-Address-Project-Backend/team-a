package com.example.address_project.global.error.exception

abstract class BusinessException (
    val errorCode : ErrorCode
) : RuntimeException()
