package com.hatchlabs.kfms.dtos

import java.sql.Date

class MilkProductionDTO {
    var id: Long? = null
    var cowId: Long? = null
    var cowName: String? = null
    var productionDate: Date? = null
    var morningMilkKgs: Long? = null
    var noonMilkKgs: Long? = null
    var eveningMilkKgs: Long? = null
    var totalMilkKgs: Long? = null
    var lactationWeek: Long? = null
    var fatContent: Long? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var comments: String? = null
    var fileUploadedId: Long? = null
    var actionBy: Long? = null
    var userName: String? = null
}