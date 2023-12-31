package com.example.address_project.domain.road_code.service

import com.example.address_project.domain.enums.Type
import com.example.address_project.domain.facade.SaveFileFacade
import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FileReaderRoadCodeService (
    private val roadCodeRepository: RoadCodeRepository,
    private val addressZipFileService: AddressZipFileService,
    private val saveFileFacade: SaveFileFacade
) {

    @Transactional
    fun readerRoadCodeKorea(file: MultipartFile, unzipFile: UnzipFile) {

        val filePath = saveFileFacade.saveFile(file)

        addressZipFileService.addressFileWriter(unzipFile)

        val lines = File(filePath).readLines()
        for (line in lines) {
            val columns = line.split("|")
            val roadCode = RoadCode(
                type = Type.ROAD_NAME,
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
