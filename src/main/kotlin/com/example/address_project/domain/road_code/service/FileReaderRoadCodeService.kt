package com.example.address_project.domain.road_code.service

import com.example.address_project.domain.enums.Type
import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.domain.road_code.presentation.dto.request.Request
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File
import java.nio.file.Files

@Service
class FileReaderRoadCodeService(
    private val roadCodeRepository: RoadCodeRepository,
) {

    @Transactional
    fun readerRoadCodeKorea(fileText: Request) {
        val file = createTempFile("road_code_temp", ".txt")

        try {
            Files.write(file.toPath(), fileText.fileText.toByteArray())

            val lines = file.readLines()
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
        } finally {
            file.delete()
        }
    }
}