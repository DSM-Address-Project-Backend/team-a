package com.example.address_project.domain.street_number.domain

import com.example.address_project.domain.enums.Type
import com.example.address_project.domain.road_addrss.domain.RoadAddress
import com.example.address_project.global.entity.BaseUUIDEntity
import java.util.*
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Table(name = "tbl_street_number")
@Entity
class StreetNumber (
    @Column(columnDefinition = "CHAR(10)")
    val legalDistrictCode: String? = null,

    @Column(columnDefinition = "VARCHAR(40)")
    val cityName: String? = null,

    @Column(columnDefinition = "VARCHAR(40)")
    val siGunGuName: String? = null,

    @Column(columnDefinition = "VARCHAR(40)")
    val legalEmdName: String? = null,

    @Column(columnDefinition = "VARCHAR(40)")
    val legalName: String? = null,

    @Column(columnDefinition = "INT(4)")
    val areaNum: Int? = null,

    @Column(columnDefinition = "INT(4)")
    val areaSubNum: Int? = null,

    @Column(columnDefinition = "VARCHAR(40)")
    val engCityName: String? = null,

    @Column(columnDefinition = "VARCHAR(40)")
    val engSiGunGuName: String? = null,

    @Column(columnDefinition = "VARCHAR(40)")
    val engLegalEmdName: String? = null,

    @Column(columnDefinition = "VARCHAR(40)")
    val engLegalName: String? = null,

    @Enumerated(EnumType.STRING)
    val type: Type? = null,

    @Column(columnDefinition = "VARCHAR(160)")
    val korFullStreetNumber: String? = null,

    @Column(columnDefinition = "VARCHAR(160)")
    val engFullStreetNumber: String? = null,

    roadAddress: RoadAddress? = null
) : BaseUUIDEntity() {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "road_address_id", columnDefinition = "BINARY(16)")
    var roadAddress = roadAddress
        protected set
}
