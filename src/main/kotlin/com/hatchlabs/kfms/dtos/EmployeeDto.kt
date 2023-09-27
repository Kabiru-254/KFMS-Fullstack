package com.hatchlabs.kfms.dtos

import java.sql.Date

class EmployeeDto {
    var id: Long? = null
    var firstName: String? = null
    var lastName: String? = null
    var position: String? = null
    var hireDate: Date? = null
    var terminationDate: Date? = null
    var salary: Double? = null
    var contactNumber: String? = null
    var email: String? = null
    var address: String? = null
    var comments: String? = null
    var fileUploadedId: Long? = null
    var actionBy: Long? = null
    var userName: String? = null
}