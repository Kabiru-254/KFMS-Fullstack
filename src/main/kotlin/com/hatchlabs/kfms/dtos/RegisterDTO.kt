package com.hatchlabs.kfms.dtos

import java.sql.Date
import java.sql.Timestamp

class RegisterDTO {
    val username: String? = null
    val firstName: String? = null
    val lastName: String? = null
    val password: String? = null
    val role: String? = null
    val email: String? = null
    val lastLogin: Timestamp? = null
    val dateCreated: Date? = null
    val createdBy: String? = null
    val profilePicture: String? = null
    val status: Long? = null
}