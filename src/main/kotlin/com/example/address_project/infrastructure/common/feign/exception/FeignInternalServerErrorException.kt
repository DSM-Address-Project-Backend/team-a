package com.example.address_project.infrastructure.common.feign.exception

import com.example.address_project.global.error.exception.BusinessException
import com.example.address_project.global.error.exception.ErrorCode

class FeignInternalServerErrorException : BusinessException(ErrorCode.FEIGN_INTERNAL_SERVER_ERROR)