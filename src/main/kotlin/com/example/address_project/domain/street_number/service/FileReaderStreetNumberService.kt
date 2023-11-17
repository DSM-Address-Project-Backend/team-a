package com.example.address_project.domain.street_number.service

import com.example.address_project.domain.road_addrss.domain.RoadAddress
import com.example.address_project.domain.road_addrss.domain.repository.RoadAddressRepository
import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.domain.road_code.exception.FileInternalError
import com.example.address_project.domain.road_code.exception.FileNotFoundException
import com.example.address_project.domain.street_number.domain.StreetNumber
import com.example.address_project.domain.street_number.domain.repository.StreetNumberRepository
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.util.UUID
import javax.transaction.Transactional

@Service
class FileReaderStreetNumberService(
        private val streetNumberRepository: StreetNumberRepository,
        private val roadAddressRepository: RoadAddressRepository
) {
    @Transactional
    fun fileReaderStreetNumber(id : UUID) {
        val file = File("") //TODO 파일명 추가
        val road = roadAddressRepository.findById(id).orElseThrow {RuntimeException("asdf")}
        if (file.exists()) {
            val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
            try {
                reader.use {
                    file.forEachLine { line ->
                        val spacing = line.split("|")

                        val streetNumber = StreetNumber( // API 설명서에 있는 순서대로 저장
                                legalDistrictCode = spacing[2],
                                cityName = spacing[3],
                                siGunGuName = spacing[4],
                                legalEmdName = spacing[5],
                                legalName = spacing[6],
                                areaNum = spacing[8].toInt(),
                                areaSubNum = spacing[9].toInt(),
                                roadAddress = road
                        )
                        streetNumberRepository.save(streetNumber)
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