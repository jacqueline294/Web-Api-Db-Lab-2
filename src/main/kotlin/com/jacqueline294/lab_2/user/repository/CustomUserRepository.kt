package com.jacqueline294.lab_2.user.repository

import com.jacqueline294.lab_2.user.model.CustomUser
import org.springframework.data.jpa.repository.JpaRepository

interface CustomUserRepository: JpaRepository<CustomUser, Long> {
    fun findByUsername(username: String): CustomUser?
}