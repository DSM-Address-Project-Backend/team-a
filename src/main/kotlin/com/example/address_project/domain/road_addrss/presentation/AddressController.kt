package com.example.address_project.domain.road_addrss.presentation

import com.example.address_project.domain.road_addrss.presentation.dto.response.AutocompleteResponse
import com.example.address_project.domain.road_addrss.presentation.dto.response.SearchResponse
import com.example.address_project.domain.road_addrss.service.AutocompleteService
import com.example.address_project.domain.road_addrss.service.SearchService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/address")
@RestController
class AddressController(
    private val autocompleteService: AutocompleteService,
    private val searchService: SearchService
) {
    @GetMapping("/help")
    fun autocomplete(@RequestParam("keyword") keyword: String): AutocompleteResponse {
        return autocompleteService.execute(keyword)
    }

    @GetMapping("/search")
    fun search(@RequestParam("page") page: Int, @RequestParam("keyword") keyword: String): SearchResponse {
        return searchService.execute(page, keyword)
    }
}
