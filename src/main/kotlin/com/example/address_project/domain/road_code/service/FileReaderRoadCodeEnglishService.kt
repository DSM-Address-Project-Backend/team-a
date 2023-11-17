package com.example.address_project.domain.road_code.service

import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.domain.road_code.exception.FileInternalError
import com.example.address_project.domain.road_code.exception.FileNotFoundException
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.transaction.Transactional

@Service
class FileReaderRoadCodeEnglishService (
    private val roadCodeRepository: RoadCodeRepository,
    private val addressZipFileService: AddressZipFileService
) {

    @Transactional
    fun fileReaderRoadCode(fileName: File) {
        val file = File(fileName.name) //TODO 파일명 추가
        if (file.exists()) {
            val reader = BufferedReader(FileReader(file))
            try {
                reader.use {
                    file.forEachLine { line ->
                        val spacing = line.split("|")

                        val roadCode = RoadCode( // API 설명서에 있는 순서대로 저장
                            engRodeName = spacing[4],
                            engCityName = spacing[15],
                            engSggName = spacing[16],
                            engEmdName = spacing[17],
                            emdSerialNum = spacing[2],
                        )
                        roadCodeRepository.save(roadCode)
                    }
                }
            } catch (error: FileInternalError) {
                throw error
            }
        } else {
            throw FileNotFoundException
        }
    }
}