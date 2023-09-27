package com.hatchlabs.kfms.models


import java.sql.Date
import jakarta.persistence.*

@Entity
@Table(name = "KFMS_EMPLOYEES_DETAILS")
class EmployeeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "FIRST_NAME")
    var firstName: String? = null

    @Column(name = "LAST_NAME")
    var lastName: String? = null

    @Column(name = "POSITION")
    var position: String? = null

    @Column(name = "HIRE_DATE")
    var hireDate: Date? = null

    @Column(name = "TERMINATION_DATE")
    var terminationDate: Date? = null

    @Column(name = "SALARY")
    var salary: Double? = null

    @Column(name = "CONTACT_NUMBER")
    var contactNumber: String? = null

    @Column(name = "EMAIL")
    var email: String? = null

    @Column(name = "ADDRESS")
    var address: String? = null

    @Column(name = "COMMENTS")
    var comments: String? = null

    @Column(name = "FILE_UPLOADED_ID")
    var fileUploadedId: Long? = null

    @Column(name = "ACTION_BY")
    var actionBy: Long? = null

}