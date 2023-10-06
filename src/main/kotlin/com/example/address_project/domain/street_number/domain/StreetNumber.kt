package com.example.address_project.domain.street_number.domain

import org.hibernate.annotations.DynamicInsert
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@DynamicInsert
@Entity(name = "tbl_street_number")
class StreetNumber (
    @Id
    @Column(columnDefinition = "CHAR(3)")
    val id: UUID,

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
)