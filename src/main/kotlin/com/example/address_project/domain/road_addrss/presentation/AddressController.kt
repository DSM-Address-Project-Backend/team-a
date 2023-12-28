package com.example.address_project.domain.road_addrss.presentation

import com.example.address_project.domain.road_addrss.presentation.dto.response.AutocompleteResponse
import com.example.address_project.domain.road_addrss.service.AutocompleteService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/address")
@RestController
class AddressController(
    private val autocompleteService: AutocompleteService
) {
    @GetMapping("/help")
    fun search(@RequestParam("keyword") keyword: String): AutocompleteResponse {
        return autocompleteService.execute(keyword)
    }
}
