package com.example.address_project.domain.street_number.service

import com.example.address_project.domain.street_number.domain.StreetNumber
import com.example.address_project.domain.street_number.domain.repository.StreetNumberRepository
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FileReadeStreetNumberEnglishService (

        @Value("\${upload.dir}")
        private var uploadDir: String,

        private val streetNumberRepository : StreetNumberRepository,

        private val addressZipFileService: AddressZipFileService
) {

    @Transactional
    fun readerEnglish(file: MultipartFile, unzipFile: UnzipFile) {

        val filePath = saveFile(file)

        addressZipFileService.addressFileWriter(unzipFile)

        saveEnglishStreetNumber(filePath)
    }

    private fun saveFile(file: MultipartFile): String {
        val uploadPath = File(uploadDir)

        if (!uploadPath.exists()) {
            uploadPath.mkdirs()
        }

        val targetLocation = File(uploadPath, file.originalFilename!!)

        file.transferTo(targetLocation)

        return targetLocation.absolutePath
    }

    private fun saveEnglishStreetNumber(filePath: String) {
        val lines = File(filePath).readLines()
        for (line in lines) {
            val columns = line.split("|")
            val streetNumber = StreetNumber(
                    engCityName = columns[3],
                    engSiGunGuName = columns[4],
                    engLegalEmdName = columns[5],
                    engLegalName = columns[6]
            )

            streetNumberRepository.save(streetNumber)
        }
    }
}
