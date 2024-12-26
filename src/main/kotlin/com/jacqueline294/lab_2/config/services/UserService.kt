package com.jacqueline294.lab_2.config.services

import com.jacqueline294.lab_2.user.model.CustomUser
import com.jacqueline294.lab_2.user.repository.CustomUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder

class UserService(@Autowired private val userRepository: CustomUserRepository, private val passwordEncoder: PasswordEncoder) {

    fun registerUser(username: String, password: String): CustomUser {
        val hashedPassword = passwordEncoder.encode(password)
        val user = CustomUser(username = username, password = hashedPassword)
        return userRepository.save(user)
    }

    fun authenticateUser(username: String, password: String): CustomUser? {
        val user = userRepository.findByUsername(username) ?: return null
        return if (passwordEncoder.matches(password, user.password)) user else null
    }

    fun deleteUser(userId: Long): Boolean {
        return if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId)
            true
        } else {
            false
        }
    }
    fun findAllUsers(): List<CustomUser> = userRepository.findAll()
}


