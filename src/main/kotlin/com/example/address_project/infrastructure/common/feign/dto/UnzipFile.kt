package com.example.address_project.infrastructure.common.feign.dto

data class UnzipFile (
    val reqType: String,
    val zipFilePath: String,
    val unzipTargetDirectoryPath: String,
    val year: String,
    val month: String,
)