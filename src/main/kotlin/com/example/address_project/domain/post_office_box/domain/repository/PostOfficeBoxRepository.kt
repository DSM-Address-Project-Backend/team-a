package com.example.address_project.domain.post_office_box.domain.repository

import com.example.address_project.domain.post_office_box.domain.PostOfficeBox
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PostOfficeBoxRepository : CrudRepository<PostOfficeBox, UUID> {
    @Query(value = "SELECT * FROM tbl_post_office_box WHERE MATCH(po_box_name) AGAINST(:name in boolean Mode)", nativeQuery = true)
    fun findAllByPoBoxName(name: String): List<PostOfficeBox>
}
