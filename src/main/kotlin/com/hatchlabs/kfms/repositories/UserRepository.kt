package com.hatchlabs.kfms.repositories

import com.hatchlabs.kfms.models.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByEmail(email: String?): User?

    fun findById(id: Long?): User?

    fun findByUsername(username: String?): User?

}