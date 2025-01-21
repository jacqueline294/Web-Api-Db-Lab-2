package com.jacqueline294.lab_2.user.repository

import com.jacqueline294.lab_2.user.model.ShoppingItem
import org.springframework.data.jpa.repository.JpaRepository

interface ShoppingItemRepository : JpaRepository<ShoppingItem, Long> {
    fun findByUserId(userId: Long): List<ShoppingItem>
    abstract fun findByIdAndUserId(id: Long, userId: Long): Any
}