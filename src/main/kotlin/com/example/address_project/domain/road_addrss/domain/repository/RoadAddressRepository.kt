package com.example.address_project.domain.road_addrss.domain.repository

import com.example.address_project.domain.road_addrss.domain.RoadAddress
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface RoadAddressRepository : CrudRepository<RoadAddress, UUID> {
}