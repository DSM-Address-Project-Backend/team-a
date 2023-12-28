package com.example.address_project.domain.street_number.domain.repository

import com.example.address_project.domain.street_number.domain.StreetNumber
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface StreetNumberRepository : CrudRepository<StreetNumber, UUID> {
    @Query(value = "SELECT * FROM tbl_street_number WHERE MATCH(kor_full_street_number) AGAINST(:name in boolean Mode)", nativeQuery = true)
    fun findAllByKorFullStreetNumber(name: String): List<StreetNumber>
}
