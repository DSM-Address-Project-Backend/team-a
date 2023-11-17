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
    fun fileReaderRoadCode() {

    }
}
