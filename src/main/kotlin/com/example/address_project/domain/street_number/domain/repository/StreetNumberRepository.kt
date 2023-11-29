package com.example.address_project.domain.street_number.domain.repository

import com.example.address_project.domain.street_number.domain.StreetNumber
import org.springframework.data.repository.CrudRepository
import java.util.*

interface StreetNumberRepository : CrudRepository<StreetNumber, UUID> {
}
