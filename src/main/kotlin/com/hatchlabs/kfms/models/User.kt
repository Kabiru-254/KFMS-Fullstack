package com.hatchlabs.kfms.models

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.sql.Date
import java.sql.Timestamp

@Entity
@Table(name = "KFMS_USERS")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "USERNAME")
    var username: String? = null

    @Column(name = "FIRST_NAME")
    var firstName: String? = null

    @Column(name = "LAST_NAME")
    var lastName: String? = null

    @Column(name = "USER_PASSWORD")
    var password: String? = null
        @JsonIgnore
        get() = field
        set(value) {
            field = BCryptPasswordEncoder().encode(value)
        }

    fun comparePasswords(password: String?): Boolean {
        return BCryptPasswordEncoder().matches(password, this.password)

    }

    @Column(name = "ROLE")
    var role: String? = null

    @Column(name = "EMAIL", unique = true)
    var email: String? = null

    @Column(name = "LAST_LOGIN")
    var lastLogin: Timestamp? = null

    @Column(name = "DATE_CREATED")
    var dateCreated: Date? = null

    @Column(name = "CREATED_BY")
    var createdBy: String? = null

    @Column(name = "PROFILE_PICTURE")
    var profilePicture: String? = null

    @Column(name = "USER_STATUS")
    var status: Long? = null
}