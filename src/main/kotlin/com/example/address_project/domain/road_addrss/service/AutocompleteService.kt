package com.example.address_project.domain.road_addrss.service

import com.example.address_project.domain.post_office_box.domain.repository.PostOfficeBoxRepository
import com.example.address_project.domain.road_addrss.presentation.dto.response.AutocompleteResponse
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.domain.street_number.domain.repository.StreetNumberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AutocompleteService(
    private val roadCodeRepository: RoadCodeRepository,
    private val streetNumberRepository: StreetNumberRepository,
    private val postOfficeBoxRepository: PostOfficeBoxRepository,
) {
    @Transactional(readOnly = true)
    fun execute(keyword: String): AutocompleteResponse {
        val roadCodes = roadCodeRepository.findAllByKorFullRodeCode(keyword).map { it.korFullRodeCode }
        val postOfficeBoxNames = postOfficeBoxRepository.findAllByPoBoxName(keyword).map { it.poBoxName }
        val streetNumbers = streetNumberRepository.findAllByKorFullStreetNumber(keyword).map { it.korFullStreetNumber }
        val item = (roadCodes + postOfficeBoxNames + streetNumbers).filterNot { it.isNullOrEmpty() }

        return if (item.isEmpty() || item.size < 9) {
            AutocompleteResponse(item)
        } else {
            AutocompleteResponse(item.subList(0, 9))
        }
    }
}
