package com.example.address_project.domain.road_addrss.service

import com.example.address_project.domain.post_office_box.domain.repository.PostOfficeBoxRepository
import com.example.address_project.domain.road_addrss.domain.repository.RoadAddressRepository
import com.example.address_project.domain.road_addrss.presentation.dto.response.Name
import com.example.address_project.domain.road_addrss.presentation.dto.response.SearchElement
import com.example.address_project.domain.road_addrss.presentation.dto.response.SearchResponse
import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.domain.street_number.domain.repository.StreetNumberRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class SearchService(
    private val roadCodeRepository: RoadCodeRepository,
    private val streetNumberRepository: StreetNumberRepository,
    private val postOfficeBoxRepository: PostOfficeBoxRepository,
    private val roadAddressRepository: RoadAddressRepository,
) {
    @Transactional(readOnly = true)
    fun execute(page: Int, keyword: String): SearchResponse {
        val pageable = PageRequest.of(page, 10)
        val roadCodes: Page<RoadCode> = roadCodeRepository.findByKorFullRodeCode(keyword, pageable)

        return SearchResponse(
            roadCodes.content.map { createSearchElement(it, keyword) }
        )
    }

    private fun createSearchElement(roadCode: RoadCode, keyword: String): SearchElement {
        return SearchElement(
            type = roadCode.type!!.number,
            postalCode = roadCode.postalCode.toString(),
            representAddressName = getRepresentAddressName(roadCode),
            jibuns = getJibuns(roadCode),
            roadName = getRoadNames(roadCode),
            post = getPost(keyword)
        )
    }

    private fun getJibuns(roadCode: RoadCode): List<Name> {
        return streetNumberRepository.findAllByRoadCode(roadCode)
            .map { Name(it.korFullStreetNumber!!, it.engFullStreetNumber!!) }
    }

    private fun getRoadNames(roadCode: RoadCode): List<Name> {
        return roadAddressRepository.findAllByRoadCode(roadCode)
            .map { Name(roadCode.korFullRodeCode!!, roadCode.engFullRodeCode!!) }
    }

    private fun getPost(keyword: String): String? {
        return postOfficeBoxRepository.findAllByPoBoxName(keyword).firstOrNull()?.poBoxName
    }

    private fun getRepresentAddressName(roadCode: RoadCode): String {
        return if (roadCode.isRepresent == true) roadCode.roadName!! else ""
    }
}
