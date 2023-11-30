package com.example.address_project.domain.road_code.service

import com.example.address_project.domain.facade.SaveFileFacade
import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.infrastructure.common.feign.dto.UnzipFile
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.io.File

@Service
class FileReaderRoadCodeEnglishService (


    private val roadCodeRepository: RoadCodeRepository,
    private val saveFileFacade: SaveFileFacade,
    private val addressZipFileService: AddressZipFileService
) {

    @Transactional
    fun readerRoadCodeEnglish(file: MultipartFile, unzipFile: UnzipFile) {

        val filePath = saveFileFacade.saveFile(file)

        addressZipFileService.addressFileWriter(unzipFile)
        val lines = File(filePath).readLines()
        for (line in lines) {
            val columns = line.split("|")
            val roadCode = RoadCode(
                engRodeName = columns[4],
                engCityName = columns[15],
                engSggName = columns[16],
                engEmdName = columns[17]
            )

            roadCodeRepository.save(roadCode)
        }
    }
}
