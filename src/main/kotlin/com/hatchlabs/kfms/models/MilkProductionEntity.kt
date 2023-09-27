package com.hatchlabs.kfms.models

import jakarta.persistence.*
import java.sql.Date


@Entity
@Table(name = "KFMS_MILK_PRODUCTION_DETAILS")
class MilkProductionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "COW_ID")
    var cowId: Long? = null

    @Column(name = "PRODUCTION_DATE")
    var productionDate: Date? = null

    @Column(name = "MORNING_MILK_KGS")
    var morningMilkKgs: Long? = null

    @Column(name = "NOON_MILK_KGS")
    var noonMilkKgs: Long? = null

    @Column(name = "EVENING_MILK_KGS")
    var eveningMilkKgs: Long? = null

    @Column(name = "TOTAL_MILK_KGS")
    var totalMilkKgs: Long? = null

    @Column(name = "LACTATION_WEEK")
    var lactationWeek: Long? = null

    @Column(name = "FAT_CONTENT")
    var fatContent: Long? = null

    @Column(name = "CREATED_AT")
    var createdAt: Date? = null

    @Column(name = "UPDATED_AT")
    var updatedAt: Date? = null

    @Column(name = "COMMENTS")
    var comments: String? = null

    @Column(name = "FILE_UPLOADED_ID")
    var fileUploadedId: Long? = null

    @Column(name = "ACTION_BY")
    var actionBy: Long? = null



}