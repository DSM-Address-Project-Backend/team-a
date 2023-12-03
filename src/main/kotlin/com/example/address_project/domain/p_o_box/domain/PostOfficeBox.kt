package com.example.address_project.domain.p_o_box.domain

import com.example.address_project.global.entity.BaseUUIDEntity
import javax.persistence.Entity
import javax.persistence.Table

@Table(name = "tbl_post_office_box")
@Entity
class PostOfficeBox (
    val poBoxName: String
): BaseUUIDEntity()
