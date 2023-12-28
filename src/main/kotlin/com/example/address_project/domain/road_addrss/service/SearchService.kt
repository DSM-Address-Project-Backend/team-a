package com.example.address_project.domain.road_addrss.service

import com.example.address_project.domain.post_office_box.domain.repository.PostOfficeBoxRepository
import com.example.address_project.domain.road_addrss.presentation.dto.response.SearchResponse
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.domain.street_number.domain.repository.StreetNumberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchService(
    private val roadCodeRepository: RoadCodeRepository,
    private val streetNumberRepository: StreetNumberRepository,
    private val postOfficeBoxRepository: PostOfficeBoxRepository,
) {
    @Transactional(readOnly = true)
    fun execute(keyword: String): SearchResponse {
        val roadCodes = roadCodeRepository.findAllByKorFullRodeCode(keyword).map { it.korFullRodeCode }.filterNot { it.isNullOrEmpty() }
        val postOfficeBoxNames = postOfficeBoxRepository.findAllByPoBoxName(keyword).map { it.poBoxName }.filterNot { it.isEmpty() }
        val streetNumbers = streetNumberRepository.findAllByKorFullStreetNumber(keyword).map { it.korFullStreetNumber }.filterNot { it.isNullOrEmpty() }
        val item = roadCodes + postOfficeBoxNames + streetNumbers

        return SearchResponse(item)
    }
}
