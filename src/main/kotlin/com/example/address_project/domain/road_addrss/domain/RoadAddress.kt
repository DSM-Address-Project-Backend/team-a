package com.example.address_project.domain.road_addrss.domain
import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.global.entity.BaseUUIDEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "tbl_road_address")
@Entity
class RoadAddress(
    @Column(columnDefinition = "CHAR(26)")
    val managementNumber: String? = null,

    @Column(columnDefinition = "INT(5)")
    val buildingNum: Int? = null,

    @Column(columnDefinition = "INT(5)")
    val buildingSubNum: Int? = null,

    @Column(columnDefinition = "CHAR(5)")
    val postNumber: String? = null,

    @Column(columnDefinition = "BIT(5)")
    val isGiveDetailAddress: Boolean? = null,

    roadCodeEntity: RoadCode? = null
): BaseUUIDEntity() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "road_coad_id", columnDefinition = "BINARY(16)")
    var roadCodeEntity = roadCodeEntity
        protected set
}