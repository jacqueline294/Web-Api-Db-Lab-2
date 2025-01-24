package com.jacqueline294.lab_2.user.controller

import com.jacqueline294.lab_2.user.dto.ItemDto
import com.jacqueline294.lab_2.user.model.CustomUser
import com.jacqueline294.lab_2.user.model.ShoppingItem
import com.jacqueline294.lab_2.user.repository.CustomUserRepository
import com.jacqueline294.lab_2.user.repository.ShoppingItemRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/v1/shopping-list")
class ShoppingListController(private val shoppingItemRepository: ShoppingItemRepository, private val customUserRepository: CustomUserRepository) {

    @PostMapping
    fun addItem(@RequestBody @Valid itemDto: ItemDto): String {
        val user = customUserRepository.findById(itemDto.userId).orElseThrow {
            IllegalArgumentException("User not found with ID: ${itemDto.userId}")
        }
        val item = ShoppingItem(itemName = itemDto.itemName, quantity = itemDto.quantity, user = user)
        shoppingItemRepository.save(item)
        return "Item added successfully"
    }

    @GetMapping
    fun getShoppingList(): List<ShoppingItem> {
        return shoppingItemRepository.findAll()
    }
    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: Long, @RequestBody @Valid itemDto: ItemDto): ResponseEntity<String> {
        val existingItem = shoppingItemRepository.findById(id).orElseThrow {
            IllegalArgumentException("Item not found with ID: $id")
        }

        val updatedItem = existingItem.copy(
            itemName = itemDto.itemName,
            quantity = itemDto.quantity
        )

        shoppingItemRepository.save(updatedItem)
        return ResponseEntity.ok("Item updated successfully")
    }


    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Long): String {
        shoppingItemRepository.deleteById(id)
        return "Item removed successfully"
    }
}
