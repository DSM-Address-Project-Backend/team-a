package com.example.address_project.domain.road_code.domain

import com.example.address_project.domain.enums.Type
import com.example.address_project.global.entity.BaseUUIDEntity
import javax.persistence.*

@Table(name = "tbl_road_code")
@Entity
class RoadCode(

    @Column(columnDefinition = "VARCHAR(80)")
    val roadName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)")
    val cityName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)")
    val sggName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)")
    val emdName: String? = null,

    @Column(columnDefinition = "TINYINT(1)")
    val emdSection: Boolean? = null,

    @Column(columnDefinition = "CHAR(3)")
    val emdCode: String? = null,

    @Column(columnDefinition = "VARCHAR(80)")
    val engRodeName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)")
    val engCityName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)")
    val engSggName: String? = null,

    @Column(columnDefinition = "VARCHAR(20)")
    val engEmdName: String? = null,

    @Column(columnDefinition = "CHAR(2)")
    val emdSerialNum: String? = null,

    @Column(columnDefinition = "CHAR(12)")
    val roadNameCode: String? = null,

    @Enumerated(EnumType.STRING)
    val type: Type? = null,

    @Column(columnDefinition = "VARCHAR(60)")
    val korFullRodeCode: String? = null,

    @Column(columnDefinition = "VARCHAR(60)")
    val engFullRodeCode: String? = null,

) : BaseUUIDEntity()
