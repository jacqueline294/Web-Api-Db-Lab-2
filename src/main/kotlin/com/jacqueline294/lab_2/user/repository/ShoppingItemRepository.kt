package com.jacqueline294.lab_2.user.repository

import com.jacqueline294.lab_2.user.model.ShoppingItem
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ShoppingItemRepository : JpaRepository<ShoppingItem, Long> {
    fun findByUser_Id(userId: Long): List<ShoppingItem>
}

