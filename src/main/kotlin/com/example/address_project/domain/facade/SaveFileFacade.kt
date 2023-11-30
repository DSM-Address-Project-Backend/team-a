package com.example.address_project.domain.facade

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Configuration
class SaveFileFacade(
    @Value("\${upload.dir}")
    private var uploadDir: String
) {

    fun saveFile(file: MultipartFile): String {
        val uploadPath = File(uploadDir)

        if (!uploadPath.exists()) {
            uploadPath.mkdirs()
        }

        val targetLocation = File(uploadPath, file.originalFilename!!)

        file.transferTo(targetLocation)

        return targetLocation.absolutePath
    }
}