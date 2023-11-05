package com.example.address_project.domain.street_number.service

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
import javax.transaction.Transactional

@Service
class FileReaderStreetNumberService(
        private val streetNumberRepository: StreetNumberRepository
) {
    @Transactional
    fun fileReaderStreetNumber() {
        val file = File("") //TODO 파일명 추가
        if (file.exists()) {
            val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
            try {
                reader.use {
                    file.forEachLine { line ->
                        val spacing = line.split("|")

                        val streetNumber = StreetNumber( // API 설명서에 있는 순서대로 저장
                                legalDistrictCode = spacing[],
                                cityName = spacing[],
                                siGunGuName = spacing[],
                                legalEmdName = spacing[],
                                legalName = spacing[],
                                areaNum = spacing[],
                                areaSubNum = spacing[],
                                engCityName = spacing[],
                                engSiGunGuName = spacing[],
                                engLegalEmdName = spacing[],
                                engLegalName = spacing[]
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