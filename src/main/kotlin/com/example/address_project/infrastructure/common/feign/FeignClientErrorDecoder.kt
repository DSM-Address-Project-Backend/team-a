package com.example.address_project.infrastructure.common.feign

import com.example.address_project.infrastructure.common.feign.exception.FeignBadRequestException
import com.example.address_project.infrastructure.common.feign.exception.FeignInternalServerError
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignClientErrorDecoder : ErrorDecoder {

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