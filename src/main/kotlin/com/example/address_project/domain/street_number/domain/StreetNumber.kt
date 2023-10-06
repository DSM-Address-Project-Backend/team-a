package com.example.address_project.domain.street_number.domain

import com.example.address_project.domain.road_addrss.domain.RoadAddress
import com.example.address_project.global.entity.BaseUUIDEntity
import org.hibernate.annotations.DynamicInsert
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType.LAZY
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@DynamicInsert
@Entity(name = "tbl_street_number")
class StreetNumber (
    @Column(columnDefinition = "CHAR(10)", nullable = false)
    val legalDistrictCode: String,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val cityName: String,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val siGunGuName: String,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val legalEmdName: String,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val legalName: String,

    @Column(columnDefinition = "INT(4)", nullable = false)
    val areaNum: Int,

    @Column(columnDefinition = "INT(4)", nullable = false)
    val areaSubNum: Int,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val engCityName: String,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val engSiGunGuName: String,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val engLegalEmdName: String,

    @Column(columnDefinition = "VARCHAR(40)", nullable = false)
    val engLegalName: String,

    roadAddress: RoadAddress
) : BaseUUIDEntity() {
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "road_address_id", columnDefinition = "BINARY(16)", nullable = false)
    var roadAddress = roadAddress
        protected set
}