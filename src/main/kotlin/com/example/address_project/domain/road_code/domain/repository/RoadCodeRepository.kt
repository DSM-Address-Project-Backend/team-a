package com.example.address_project.domain.road_code.domain.repository

import com.example.address_project.domain.road_code.domain.RoadCode
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RoadCodeRepository : CrudRepository<RoadCode, UUID> {
    @Query(value = "SELECT * FROM tbl_road_code WHERE MATCH(kor_full_rode_code) AGAINST(:name in boolean Mode)", nativeQuery = true)
    fun findAllByKorFullRodeCode(name: String): List<RoadCode>
}
