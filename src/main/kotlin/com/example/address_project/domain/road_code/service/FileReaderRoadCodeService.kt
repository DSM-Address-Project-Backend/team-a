package com.example.address_project.domain.road_code.service

import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.transaction.Transactional

@Service
class FileReaderRoadCodeService(
    private val roadCodeRepository: RoadCodeRepository
) {
    @Transactional
    fun fileReaderRoadCode() {
        val file = File("") //TODO 파일명 추가
        if (file.exists()) {
            val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
            try {
                reader.use {
                    file.forEachLine { line ->
                        val spacing = line.split("|")

                        val roadCode = RoadCode(
                            roadName = spacing[4],
                            cityName = spacing[6],
                            sggName = spacing[7],
                            emdName = spacing[10],
                            emdSection = spacing[8].toBoolean(),
                            emdCode = spacing[1],
                            engRodeName = spacing[4],
                            engCityName = spacing[15],
                            engSggName = spacing[16],
                            engEmdName = spacing[17],
                            emdSerialNum = spacing[2],
                            roadNameCode = spacing[1] + spacing[2]
                        )
                        roadCodeRepository.save(roadCode)
                    }
                }
            } catch (e:Exception) {
                e.printStackTrace() //TODO 예외 추가
            }
        } else {
            throw Exception() // TODO 예외 추가
        }
    }
}
