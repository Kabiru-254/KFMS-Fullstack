package com.hatchlabs.kfms.models

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "KFMS_COW_DETAILS")
class CowEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "NAME")
    var name: String? = null

    @Column(name = "EAR_TAG")
    var earTag: String? = null

    @Column(name = "AGE")
    var age: Long? = null

    @Column(name = "COLOR")
    var color: String? = null

    @Column(name = "BREED")
    var breed: String? = null

    @Column(name = "DATE_OF_BIRTH")
    var dateOfBirth: Date? = null

    @Column(name = "WEIGHT")
    var weight: Long? = null

    @Column(name = "DATE_ADDED")
    var dateAdded: Date? = null

    @Column(name = "STATUS")
    var status: Long? = null

    @Column(name = "COMMENTS")
    var comments: String? = null

    @Column(name = "FILE_UPLOADED_ID")
    var file_uploaded_id: Long? = null

    @Column(name = "ACTION_BY")
    var actionBy: Long? = null
}