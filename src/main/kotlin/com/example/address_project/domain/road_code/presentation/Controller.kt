package com.example.address_project.domain.road_code.presentation

import com.example.address_project.domain.road_code.presentation.dto.request.Request
import com.example.address_project.domain.road_code.service.FileReaderRoadCodeService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller(
    private val fileReaderRoadCodeService: FileReaderRoadCodeService
) {
    @PostMapping("/road")
    fun a(request: Request) = fileReaderRoadCodeService.readerRoadCodeKorea(request)
}