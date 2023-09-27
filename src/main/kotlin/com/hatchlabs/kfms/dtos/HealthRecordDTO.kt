package com.hatchlabs.kfms.dtos

import java.sql.Date

class HealthRecordDTO {
    var id: Long? = null
    var cowID: Long? = null
    var cowName: String? = null
    var event: String? = null
    var diagnosis: String? = null
    var vet: String? = null
    var recordDate: Date? = null
    var treatmentCost: Long? = null
    var followUp: Boolean? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var status: Long? = null
    var comments: String? = null
    var fileUploadedId: Long? = null
    var actionBy: Long? = null
    var userName: String? = null

}