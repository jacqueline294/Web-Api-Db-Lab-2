package com.jacqueline294.lab_2.config.services

import com.jacqueline294.lab_2.user.model.ShoppingItem
import com.jacqueline294.lab_2.user.repository.ShoppingItemRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ShoppingItemService(@Autowired private val repository: ShoppingItemRepository) {

    fun getAllItems(userId: Long): List<ShoppingItem> = repository.findByUserId(userId)

    fun getItemById(id: Long, userId: Long): ShoppingItem? {
        val item = repository.findById(id).orElse(null)
        return if (item?.userId == userId) item else null
    }

    fun addItem(item: ShoppingItem): ShoppingItem = repository.save(item)

    fun updateItem(id: Long, userId: Long, updatedItem: ShoppingItem): ShoppingItem? {
        val existingItem = repository.findById(id).orElse(null) ?: return null
        return if (existingItem.userId == userId) {
            val newItem = existingItem.copy(name = updatedItem.name, quantity = updatedItem.quantity)
            repository.save(newItem)
        } else {
            null
        }
    }

    fun deleteItem(id: Long, userId: Long): Boolean {
        val existingItem = repository.findById(id).orElse(null) ?: return false
        return if (existingItem.userId == userId) {
            repository.deleteById(id)
            true
        } else {
            false
        }
    }
}
