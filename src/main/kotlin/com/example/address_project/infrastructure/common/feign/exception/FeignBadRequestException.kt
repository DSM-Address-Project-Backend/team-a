package com.example.address_project.infrastructure.common.feign.exception

import com.example.address_project.global.error.exception.BusinessException
import com.example.address_project.global.error.exception.ErrorCode

class FeignBadRequestException : BusinessException(ErrorCode.FEIGN_BAD_REQUEST)