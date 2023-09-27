package com.hatchlabs.kfms.models

import java.sql.Date
import jakarta.persistence.*

@Entity
@Table(name = "KFMS_EXPENSES_DETAILS")
class ExpenseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "EXPENSE_DESCRIPTION")
    var expenseDescription: String? = null

    @Column(name = "EXPENSE_DATE")
    var expenseDate: Date? = null

    @Column(name = "USER_ID")
    var userId: Long? = null

    @Column(name = "USER_NAME")
    var userName: String? = null

    @Column(name = "AMOUNT")
    var amount: Double? = null

    @Column(name = "PURPOSE")
    var purpose: String? = null

    @Column(name = "EXPENSE_CATEGORY")
    var expenseCategory: String? = null

    @Column(name = "CREATED_AT")
    var createdAt: Date? = null

    @Column(name = "UPDATED_AT")
    var updatedAt: Date? = null

    @Column(name = "STATUS")
    var status: String? = null

    @Column(name = "COMMENTS")
    var comments: String? = null

    @Column(name = "FILE_UPLOADED_ID")
    var fileUploadedId: Long? = null

    @Column(name = "ACTION_BY")
    var actionBy: Long? = null

}