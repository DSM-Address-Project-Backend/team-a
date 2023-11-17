package com.example.address_project.infrastructure.common.error

import com.example.address_project.infrastructure.common.exception.FeignBadRequestException
import com.example.address_project.infrastructure.common.exception.FeignInternalServerError
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder
import java.lang.Exception

class FeignClientErrorDecoder: ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            when (response.status()) {
                400 -> throw FeignBadRequestException
                else -> throw FeignInternalServerError
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }
}