package com.example.address_project.domain.street_number.service

import com.example.address_project.domain.road_address.domain.RoadAddress
import com.example.address_project.domain.road_address.domain.repository.RoadAddressRepository
import com.example.address_project.domain.road_code.exception.FileInternalError
import com.example.address_project.domain.road_code.exception.FileNotFoundException
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.UUID
import javax.transaction.Transactional

@Service
class FileReaderRoadAddressService(
        private val roadAddressRepository: RoadAddressRepository,
) {
    @Transactional
    fun fileReaderRoadAddress(id:UUID, fileName: File) {
        val file = File(fileName.name) //TODO 파일명 추가
        if (file.exists()) {
            val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
            try {
                reader.use {
                    file.forEachLine { line ->
                        val spacing = line.split("|")

                        val roadAddress = RoadAddress( // API 설명서에 있는 순서대로 저장
                                managementNumber = spacing[1],
                                buildingNum = spacing[13].toInt(),
                                buildingSubNum = spacing[14].toInt(),
                                postNumber = spacing[17]
                        )
                        roadAddressRepository.save(roadAddress)
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