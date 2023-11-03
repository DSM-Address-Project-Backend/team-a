package com.example.address_project.domain.road_code.service

import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import javax.transaction.Transactional

@Service
class RoadCodeClientService(
    private val roadCodeRepository: RoadCodeRepository
) {
    @Transactional
    fun fileReaderRoadCode() {
        val file = File("") // 파일명 추가하기
        if (file.exists()) {
            val reader = BufferedReader(FileReader(file, Charsets.UTF_8))
            try {
                file.forEachLine { line ->
                    val spacing = line.split("|")



                    roadCodeRepository.save()
                }
            }
        }
    }
}