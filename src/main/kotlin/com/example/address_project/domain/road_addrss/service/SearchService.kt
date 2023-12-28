package com.example.address_project.domain.road_addrss.service

import com.example.address_project.domain.post_office_box.domain.repository.PostOfficeBoxRepository
import com.example.address_project.domain.road_addrss.domain.repository.RoadAddressRepository
import com.example.address_project.domain.road_addrss.presentation.dto.response.Name
import com.example.address_project.domain.road_addrss.presentation.dto.response.SearchElement
import com.example.address_project.domain.road_addrss.presentation.dto.response.SearchResponse
import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.domain.road_code.domain.repository.RoadCodeRepository
import com.example.address_project.domain.street_number.domain.repository.StreetNumberRepository
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
        val roadCodes: List<RoadCode> = roadCodeRepository.findAllByKorFullRodeCode(keyword)

        return SearchResponse(
            roadCodes.map { roadCode ->
                val jibuns = streetNumberRepository.findAllByRoadCode(roadCode)
                    .map { Name(it.korFullStreetNumber!!, it.engFullStreetNumber!!) }
                val roadNames = roadAddressRepository.findAllByRoadCode(roadCode)
                    .map { Name(roadCode.korFullRodeCode!!, roadCode.engFullRodeCode!!) }
                val post = postOfficeBoxRepository.findAllByPoBoxName(keyword).map { it.poBoxName }

                SearchElement(
                    type = roadCode.type as Int,
                    postalCode = roadCode.postalCode.toString(),
                    representAddressName = if (roadCode.isRepresent == true) roadCode.roadName!! else "",
                    jibuns = jibuns,
                    roadName = roadNames,
                    post = post.firstOrNull()
                )
            }
        )
    }
}