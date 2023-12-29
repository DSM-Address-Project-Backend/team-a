package com.example.address_project.domain.road_addrss.service

import com.example.address_project.domain.road_addrss.domain.RoadAddress
import com.example.address_project.domain.road_addrss.domain.repository.RoadAddressRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File

@Service
class FileReaderRoadAddressService (
        private val roadAddressRepository: RoadAddressRepository,
) {

    @Transactional
    fun readerRoadAddressKorea(file: File) {
        val filePath = file.path

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
