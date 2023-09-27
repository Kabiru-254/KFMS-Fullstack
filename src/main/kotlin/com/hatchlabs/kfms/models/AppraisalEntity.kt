package com.hatchlabs.kfms.models

import java.sql.Date
import jakarta.persistence.*

@Entity
@Table(name = "KFMS_APPRAISAL_DETAILS")
class AppraisalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "EMPLOYEE_ID")
    var employeeID: Long? = null

    @Column(name = "APPRAISAL_DATE")
    var appraisalDate: Date? = null

    @Column(name = "APPRAISAL_OFFICER")
    var appraisalOfficer: Long? = null

    @Column(name = "PERFORMANCE_RATING")
    var performanceRating: Long? = null

    @Column(name = "COMMENTS")
    var comments: String? = null

    @Column(name = "FILE_UPLOADED_ID")
    var fileUploadedId: Long? = null

    @Column(name = "ACTION_BY")
    var actionBy: Long? = null
}