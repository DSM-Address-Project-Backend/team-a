package com.example.address_project.domain.post_office_box.domain

import com.example.address_project.domain.enums.Type
import com.example.address_project.global.entity.BaseUUIDEntity
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Table

@Table(name = "tbl_post_office_box")
@Entity
class PostOfficeBox (
    val poBoxName: String,
    @Enumerated(EnumType.STRING)
    val type: Type
): BaseUUIDEntity()
