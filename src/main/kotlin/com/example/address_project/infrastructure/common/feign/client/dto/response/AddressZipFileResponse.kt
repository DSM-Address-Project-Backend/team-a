package com.example.address_project.infrastructure.common.feign.client.dto.response

import org.springframework.web.multipart.MultipartFile

data class AddressZipFileResponse(
    val addressZipFile: MultipartFile
)