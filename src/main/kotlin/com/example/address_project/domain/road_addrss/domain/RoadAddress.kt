package com.example.address_project.domain.road_addrss.domain
import com.example.address_project.domain.enums.Type
import com.example.address_project.domain.road_code.domain.RoadCode
import com.example.address_project.global.entity.BaseUUIDEntity
import javax.persistence.*

@Table(name = "tbl_road_address")
@Entity
class RoadAddress(
    @Column(columnDefinition = "CHAR(26)", nullable = false)
    val managementNumber: String,

    @Column(columnDefinition = "INT(5)", nullable = false)
    val buildingNum: Int,

    @Column(columnDefinition = "INT(5)", nullable = false)
    val buildingSubNum: Int,

    @Column(columnDefinition = "CHAR(5)", nullable = false)
    val postNumber: String,

    @Enumerated(EnumType.STRING)
    val type: Type? = null,

    roadCodeEntity: RoadCode? = null
): BaseUUIDEntity() {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "road_coad_id", columnDefinition = "BINARY(16)")
    var roadCodeEntity = roadCodeEntity
        protected set
}
