package com.example.address_project.infrastructure.common.feign.dto.request

data class UnZipFileRequest(
    val reqType: String,
    val year: String,
    val month: String,
    val fileName: String,
    val realFileName: String,
    val zipPath: String,
    val unzipPath: String,
)
