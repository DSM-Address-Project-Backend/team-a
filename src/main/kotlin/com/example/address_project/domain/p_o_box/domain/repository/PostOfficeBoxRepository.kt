package com.example.address_project.domain.p_o_box.domain.repository

import com.example.address_project.domain.p_o_box.domain.PostOfficeBox
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PostOfficeBoxRepository: CrudRepository<PostOfficeBox, UUID>{
}
