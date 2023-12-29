package com.example.address_project.domain.post_office_box.service

import com.example.address_project.domain.enums.Type
import com.example.address_project.domain.post_office_box.domain.repository.PostOfficeBoxRepository
import com.example.address_project.domain.post_office_box.domain.PostOfficeBox
import com.example.address_project.infrastructure.common.feign.service.AddressZipFileService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.io.File

@Service
class FileReaderReaderPostOfficeBoxService(
    private val postOfficeBoxRepository: PostOfficeBoxRepository,
    private val addressZipFileService: AddressZipFileService
) {

    @Transactional
    fun readerPostOfficeBox(file: File) {
        val filePath = file.path

        val lines = File(filePath).readLines()
        for (line in lines) {
            val columns = line.split("|")
            val postOfficeBox = PostOfficeBox(
                type = Type.POST_OFFICE_BOX,
                poBoxName = columns[1]
            )

            postOfficeBoxRepository.save(postOfficeBox)
        }
    }
}