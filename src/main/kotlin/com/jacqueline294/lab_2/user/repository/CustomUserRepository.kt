package com.jacqueline294.lab_2.user.repository

import com.jacqueline294.lab_2.user.model.CustomUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param



interface CustomUserRepository: JpaRepository<CustomUser, Long> {
}