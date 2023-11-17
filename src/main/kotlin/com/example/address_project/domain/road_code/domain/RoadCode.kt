package com.example.address_project.domain.road_code.domain

import com.example.address_project.global.entity.BaseUUIDEntity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.Column

@Table(name = "tbl_road_code")
@Entity
class RoadCode(

    @Column(columnDefinition = "VARCHAR(80)", nullable = false)
    val roadName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val cityName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val sggName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val emdName: String? = null,

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    val emdSection: Boolean? = null,

    @Column(columnDefinition = "CHAR(3)", nullable = false)
    val emdCode: String? = null,

    @Column(columnDefinition = "VARCHAR(80)", nullable = false)
    val engRodeName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val engCityName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val engSggName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val engEmdName: String? = null,

    @Column(columnDefinition = "CHAR(2)", nullable = false)
    val emdSerialNum: String? = null,

    @Column(columnDefinition = "CHAR(12)", nullable = false)
    val roadNameCode: String? = null

) : BaseUUIDEntity()
