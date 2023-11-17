package com.example.address_project.infrastructure.common.exception

import com.example.address_project.global.error.exception.BusinessException
import com.example.address_project.global.error.exception.ErrorCode

object FeignInternalServerError: BusinessException(ErrorCode.FEIGN_INTERNAL_SERVER_ERROR)