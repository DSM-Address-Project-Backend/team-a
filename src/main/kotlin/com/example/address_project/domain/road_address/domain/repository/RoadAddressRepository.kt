package com.example.address_project.domain.road_address.domain.repository

import com.example.address_project.domain.road_address.domain.RoadAddress
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RoadAddressRepository : CrudRepository<RoadAddress, UUID> {
}