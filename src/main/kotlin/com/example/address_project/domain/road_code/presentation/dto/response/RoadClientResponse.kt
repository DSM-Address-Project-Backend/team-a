package com.example.address_project.domain.road_code.presentation.dto.response

import javax.persistence.Column

data class RoadClientResponse (
    @Column(columnDefinition = "VARCHAR(80)", nullable = false)
    val roadName: String,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val cityName: String,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val sggName: String,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val emdName: String,

    @Column(columnDefinition = "TINYINT(1)", nullable = false)
    val emdSection: Boolean,

    @Column(columnDefinition = "CHAR(3)", nullable = false)
    val emdCode: String,

    @Column(columnDefinition = "VARCHAR(80)", nullable = false)
    val engRodeName: String,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val engCityName: String,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val engSggName: String,

    @Column(columnDefinition = "VARCHAR(20)", nullable = false)
    val engEmdName: String,

    @Column(columnDefinition = "CHAR(2)", nullable = false)
    val emdSerialNum: String,

    @Column(columnDefinition = "CHAR(12)", nullable = false)
    val roadNameCode: String
)