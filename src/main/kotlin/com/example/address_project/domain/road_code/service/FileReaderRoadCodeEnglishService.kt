package com.example.address_project.domain.road_code.service

import com.example.address_project.domain.enums.Type
import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File

@Service
class FileReaderRoadCodeEnglishService (

    private val roadCodeRepository: RoadCodeRepository,
    private val addressZipFileService: AddressZipFileService
) {

    @Transactional
    fun readerRoadCodeEnglish(file: File) {
        val filePath = file.path

        val lines = File(filePath).readLines()
        for (line in lines) {
            val columns = line.split("|")
            val roadCode = RoadCode(
                type = Type.ROAD_NAME,
                engRodeName = columns[4],
                engCityName = columns[15],
                engSggName = columns[16],
                engEmdName = columns[17]
            )

            roadCodeRepository.save(roadCode)
        }
    }
}
