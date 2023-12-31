package com.example.address_project.domain.street_number.service

import com.example.address_project.domain.enums.Type
import com.example.address_project.domain.street_number.domain.StreetNumber
import com.example.address_project.domain.street_number.domain.repository.StreetNumberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File

@Service
class FileReaderStreetNumberService (
        private val roadCodeRepository: StreetNumberRepository
) {

    @Transactional
    fun readerStreetNumberKorea(file: File) {

        val filePath = file.path

        val lines = File(filePath).readLines()
        for (line in lines) {
            val columns = line.split("|")
            val streetNumber = StreetNumber(
                type = Type.LOCAL_NUMBER,
                legalDistrictCode = columns[2],
                cityName = columns[3],
                siGunGuName = columns[4],
                legalEmdName = columns[5],
                legalName = columns[6],
                areaNum = columns[8].toInt(),
                areaSubNum = columns[9].toInt()
            )
            roadCodeRepository.save(streetNumber)
        }
    }
}
