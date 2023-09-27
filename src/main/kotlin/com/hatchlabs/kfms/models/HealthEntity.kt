package com.hatchlabs.kfms.models


import jakarta.persistence.*
import java.util.Date


@Entity
@Table(name = "KFMS_HEALTH_DETAILS")
class HealthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "COW_ID")
    var cowID: Long? = null

    @Column(name = "COW_NAME")
    var cowName: String? = null

    @Column(name = "EVENT")
    var event: String? = null

    @Column(name = "DIAGNOSIS")
    var diagnosis: String? = null

    @Column(name = "VET")
    var vet: String? = null

    @Column(name = "RECORD_DATE")
    var recordDate: java.sql.Date? = null

    @Column(name = "TREATMENT_COST")
    var treatmentCost: Long? = null

    @Column(name = "FOLLOW_UP")
    var followUp: Boolean? = null

    @Column(name = "CREATED_AT")
    var createdAt: java.sql.Date? = null

    @Column(name = "UPDATED_AT")
    var updatedAt: java.sql.Date? = null

    @Column(name = "STATUS")
    var status: Long? = null

    @Column(name = "COMMENTS")
    var comments: String? = null

    @Column(name = "FILE_UPLOADED_ID")
    var fileUploadedId: Long? = null

    @Column(name = "ACTION_BY")
    var actionBy: Long? = null

}