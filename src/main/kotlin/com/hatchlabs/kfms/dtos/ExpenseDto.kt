package com.hatchlabs.kfms.dtos

import java.sql.Date

class ExpenseDto {
    var expenseDescription: String? = null
    var id: Long? = null
    var expenseDate: Date? = null
    var userId: Long? = null
    var userName: String? = null
    var amount: Double? = null
    var purpose: String? = null
    var expenseCategory: String? = null
    var createdAt: Date? = null
    var updatedAt: Date? = null
    var status: String? = null
    var comments: String? = null
    var fileUploadedId: Long? = null
    var actionBy: Long? = null
}