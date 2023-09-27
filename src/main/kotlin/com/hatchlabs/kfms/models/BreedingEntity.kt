package com.hatchlabs.kfms.models

import jakarta.persistence.*
import java.sql.Date

@Entity
@Table(name = "KFMS_BREEDING_DETAILS")
class BreedingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "COW_ID")
    var cowId: Long? = null

    @Column(name = "COW_NAME")
    var cowName: String? = null

    @Column(name = "HEAT_DATE")
    var heatDate: Date? = null

    @Column(name = "BREEDING_DATE")
    var breedingDate: Date? = null

    @Column(name = "EXPECTED_DELIVERY_DATE")
    var expectedDeliveryDate: Date? = null

    @Column(name = "ACTUAL_DELIVERY_DATE")
    var actualDeliveryDate: Date? = null

    @Column(name = "CALF_NAME")
    var calfName: String? = null

    @Column(name = "CALF_AGE")
    var calfAge: Long? = null

    @Column(name = "SIRE_NAME")
    var sireName: String? = null

    @Column(name = "CALVING_DIFFICULTY")
    var calvingDifficulty: String? = null

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