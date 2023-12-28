package com.example.address_project.domain.road_addrss.presentation

import com.example.address_project.domain.road_addrss.presentation.dto.response.SearchResponse
import com.example.address_project.domain.road_addrss.service.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/address")
@RestController
class AddressController(
    private val searchService: SearchService
) {
    @GetMapping
    fun search(@RequestParam("keyword") keyword: String): SearchResponse {
        return searchService.execute(keyword)
    }
}