package com.example.address_project.domain.road_code.service

import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.domain.road_code.exception.FileInternalError
import com.example.address_project.domain.road_code.exception.FileNotFoundException
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.transaction.Transactional

@Service
class FileReaderRoadCodeService(
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
                            roadName = spacing[4],
                            cityName = spacing[6],
                            sggName = spacing[7],
                            emdName = spacing[10],
                            emdSection = spacing[8].toBoolean(),
                            emdCode = spacing[1],
                            roadNameCode = spacing[1] + spacing[2]
                        )
                        roadCodeRepository.save(roadCode)
                    }
                }
            } catch(error: FileInternalError)  {
                throw error
            }
        } else {
            throw FileNotFoundException
        }
    }
}
