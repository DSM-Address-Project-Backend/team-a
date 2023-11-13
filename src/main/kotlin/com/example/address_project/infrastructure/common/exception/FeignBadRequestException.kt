package com.example.address_project.infrastructure.common.exception

import com.example.address_project.global.error.exception.BusinessException
import com.example.address_project.global.error.exception.ErrorCode

object FeignBadRequestException: BusinessException(ErrorCode.FEIGN_BAD_REQUEST)