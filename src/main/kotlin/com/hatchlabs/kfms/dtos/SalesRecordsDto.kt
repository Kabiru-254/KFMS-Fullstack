package com.hatchlabs.kfms.dtos

import java.sql.Date

class SalesRecordsDto {
    var id: Long? = null
    var saleDate: Date? = null
    var milkSoldKgs: Double? = null
    var totalPrice: Double? = null
    var userId: Long? = null
    var userName: String? = null
    var clientName: String? = null
    var clientPhone: String? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var status: String? = null
    var comments: String? = null
    var fileUploadedId: Long? = null
    var actionBy: Long? = null
    var incomeType: String? = null
}