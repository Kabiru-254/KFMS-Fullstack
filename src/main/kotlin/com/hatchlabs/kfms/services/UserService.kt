package com.hatchlabs.kfms.services

import com.hatchlabs.kfms.models.User
import com.hatchlabs.kfms.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun save(user: User): User {
        return this.userRepository.save(user)
    }

    fun findByEmail(email: String?): User?{
        return this.userRepository.findByEmail(email)
    }

    fun getUserById(id: Long?): User?{
        return this.userRepository.findById(id)
    }

}