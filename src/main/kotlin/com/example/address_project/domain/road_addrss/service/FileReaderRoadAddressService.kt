package com.example.address_project.domain.road_addrss.service

import com.example.address_project.domain.road_addrss.domain.RoadAddress
import com.example.address_project.domain.road_addrss.domain.repository.RoadAddressRepository
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FileReaderRoadAddressService (

        @Value("\${upload.dir}")
        private var uploadDir: String,
        private val roadAddressRepository: RoadAddressRepository,
        private val addressZipFileService: AddressZipFileService,
) {

    @Transactional
    fun readerKorea(reqType: String, year: String, month: String, fileName: String, realFileName: String, file: MultipartFile, unzipFile: UnzipFile) {

        val filePath = saveFile(file)

        addressZipFileService.addressFileWriter(unzipFile)

        saveRoadAddress(filePath)
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

    private fun saveRoadAddress(filePath: String) {
        val lines = File(filePath).readLines()
        for (line in lines) {
            val columns = line.split("|")
            val roadAddress = RoadAddress(
                    managementNumber = columns[1],
                    buildingNum = columns[13].toInt(),
                    buildingSubNum = columns[14].toInt(),
                    postNumber = columns[17]
            )

            roadAddressRepository.save(roadAddress)
        }
    }
}
