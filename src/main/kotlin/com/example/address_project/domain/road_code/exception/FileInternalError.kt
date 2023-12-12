package com.example.address_project.domain.road_code.exception

import com.example.address_project.global.error.exception.BusinessException
import com.example.address_project.global.error.exception.ErrorCode

object FileInternalError: BusinessException (
    ErrorCode.FILE_INTERNAL_ERROR
)
