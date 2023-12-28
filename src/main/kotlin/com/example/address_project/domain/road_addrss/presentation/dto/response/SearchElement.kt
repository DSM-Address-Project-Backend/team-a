package com.example.address_project.domain.road_addrss.presentation.dto.response

data class SearchElement(
    val type: Int,
    val postalCode: String,
    val representAddressName: String,
    val jibuns: List<Name>?,
    val roadName: List<Name>?,
    val post: String?
)

data class Name(
    val kor: String,
    val eng: String
)
