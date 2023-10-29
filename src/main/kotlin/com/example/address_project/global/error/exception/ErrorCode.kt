package com.example.address_project.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    FEIGN_BAD_REQUEST(400, "Feign Bad Request"),
    FEIGN_INTERNAL_SERVER_ERROR(500, "Feign Internal Server Error")
}