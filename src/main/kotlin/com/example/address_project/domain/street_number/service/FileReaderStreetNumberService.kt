package com.example.address_project.domain.street_number.service

import com.example.address_project.domain.facade.SaveFileFacade
import com.example.address_project.domain.street_number.domain.StreetNumber
import com.example.address_project.domain.street_number.domain.repository.StreetNumberRepository
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FileReaderStreetNumberService (

        private val roadCodeRepository: StreetNumberRepository,
        private val saveFileFacade: SaveFileFacade,
        private val addressZipFileService: AddressZipFileService
) {

    @Transactional
    fun readerKorea(file: MultipartFile, unzipFile: UnzipFile) {

        val filePath = saveFileFacade.saveFile(file)

        addressZipFileService.addressFileWriter(unzipFile)

        saveStreetNumber(filePath)
    }

    private fun saveStreetNumber(filePath: String) {
        val lines = File(filePath).readLines()
        for (line in lines) {
            val columns = line.split("|")
            val streetNumber = StreetNumber(
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
