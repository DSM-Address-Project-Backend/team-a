package com.example.address_project.domain.road_code.service

import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FileReaderRoadCodeService (

    @Value("\${upload.dir}")
    private  var uploadDir: String,
    private val roadCodeRepository: RoadCodeRepository,
    private val addressZipFileService: AddressZipFileService
) {

    @Transactional
    fun readerKorea(reqType: String, year: String, month: String, fileName: String, realFileName: String, file: MultipartFile, unzipFile: UnzipFile) {

        val filePath = saveFile(file)

        addressZipFileService.addressFileWriter(unzipFile)

        saveDatabase(filePath)
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

    private fun saveDatabase(filePath: String) {
        val lines = File(filePath).readLines()
        for (line in lines) {
            val columns = line.split("|")
            val roadCode = RoadCode(
                roadName = columns[4],
                cityName = columns[6],
                sggName = columns[7],
                emdName = columns[10],
                emdSection = columns[8].toBoolean(),
                emdCode = columns[1],
                emdSerialNum = columns[2],
                roadNameCode = columns[1] + columns[2]
            )

            roadCodeRepository.save(roadCode)
        }
    }
}
