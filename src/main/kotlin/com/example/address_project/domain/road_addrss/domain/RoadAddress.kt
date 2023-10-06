package com.example.address_project.domain.road_addrss.domain

import org.hibernate.annotations.DynamicInsert
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@DynamicInsert
@Entity(name = "tbl_road_address")
class RoadAddress(
    @Id
    @Column(columnDefinition = "BINARY(16)")
    val id: UUID,

    @Column(columnDefinition = "CHAR(26)", nullable = false)
    val managementNumber: String,

    @Column(columnDefinition = "INT(5)", nullable = false)
    val buildingNum: Int,

    @Column(columnDefinition = "INT(5)", nullable = false)
    val buildingSubNum: Int,

    @Column(columnDefinition = "CHAR(5)", nullable = false)
    val postNumber: String,

    @Column(columnDefinition = "BIT(5)", nullable = false)
    val isGiveDetailAddress: Boolean,
)