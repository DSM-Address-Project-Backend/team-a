package com.example.address_project.domain.road_code.exception

import com.example.address_project.global.error.exception.BusinessException
import com.example.address_project.global.error.exception.ErrorCode

object FileNotFoundException : BusinessException(
    ErrorCode.FILE_NOT_FOUND
)
