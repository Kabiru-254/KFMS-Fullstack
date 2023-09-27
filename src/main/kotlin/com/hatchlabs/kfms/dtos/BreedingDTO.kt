package com.hatchlabs.kfms.dtos

import java.sql.Date

class BreedingDTO {
    var id: Long? = null
    var cowId: Long? = null
    var cowName: String? = null
    var heatDate: Date? = null
    var breedingDate: Date? = null
    var expectedDeliveryDate: Date? = null
    var actualDeliveryDate: Date? = null
    var calfName: String? = null
    var calfAge: Long? = null
    var sireName: String? = null
    var calvingDifficulty: String? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var status: String? = null
    var comments: String? = null
    var fileUploadedId: Long? = null
    var actionBy: Long? = null
    var userName: String? = null
}