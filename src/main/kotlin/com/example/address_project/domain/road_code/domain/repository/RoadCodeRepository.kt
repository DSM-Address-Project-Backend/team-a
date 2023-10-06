package com.example.address_project.domain.road_code.domain.repository

import com.example.address_project.domain.road_code.domain.RoadCodeEntity
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RoadCodeRepository : CrudRepository<RoadCodeEntity, UUID> {
}