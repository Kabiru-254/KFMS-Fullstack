package com.hatchlabs.kfms.models


import java.sql.Date
import jakarta.persistence.*

@Entity
@Table(name = "KFMS_SALES_RECORDS")
class SalesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "SALE_DATE")
    var saleDate: Date? = null

    @Column(name = "MILK_SOLD_KGS")
    var milkSoldKgs: Double? = null

    @Column(name = "TOTAL_PRICE")
    var totalPrice: Double? = null

    @Column(name = "USER_ID")
    var userId: Long? = null

    @Column(name = "USER_NAME")
    var userName: String? = null

    @Column(name = "CLIENT_NAME")
    var clientName: String? = null

    @Column(name = "CLIENT_PHONE")
    var clientPhone: String? = null

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