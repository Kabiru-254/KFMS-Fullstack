package com.hatchlabs.kfms.dtos

import java.sql.Date

class AppraisalDto {
    var id: Long? = null
    var employeeID: Long? = null
    var appraisalDate: Date? = null
    var appraisalOfficer: Long? = null
    var performanceRating: Long? = null
    var comments: String? = null
    var fileUploadedId: Long? = null
    var actionBy: Long? = null
    var appraisalOfficerName: String? = null
    var employeeName: String? = null
}