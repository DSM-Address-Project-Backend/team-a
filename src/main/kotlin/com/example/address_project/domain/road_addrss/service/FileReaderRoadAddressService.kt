package com.example.address_project.domain.road_addrss.service

import com.example.address_project.domain.facade.SaveFileFacade
import com.example.address_project.domain.road_addrss.domain.RoadAddress
import com.example.address_project.domain.road_addrss.domain.repository.RoadAddressRepository
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FileReaderRoadAddressService (
        private val roadAddressRepository: RoadAddressRepository,
        private val addressZipFileService: AddressZipFileService,
        private val saveFileFacade: SaveFileFacade
) {

    @Transactional
    fun readerRoadAddressKorea(file: MultipartFile, unzipFile: UnzipFile) {

        val filePath = saveFileFacade.saveFile(file)

        addressZipFileService.addressFileWriter(unzipFile)

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
