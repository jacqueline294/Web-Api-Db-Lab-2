package com.jacqueline294.lab_2.user.controller

import com.jacqueline294.lab_2.user.model.ShoppingItem
import com.jacqueline294.lab_2.user.repository.ShoppingItemRepository
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/shopping-list")
class ShoppingListController(private val repository: ShoppingItemRepository) {

    @GetMapping
    fun getAllItems(): List<ShoppingItem> = repository.findAll()

    @PostMapping
    fun addItem(@RequestBody item: ShoppingItem): ShoppingItem = repository.save(item)

    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: Long, @RequestBody item: ShoppingItem): ShoppingItem {
        return repository.findById(id).map {
            val updatedItem = it.copy(name = item.name, quantity = item.quantity)
            repository.save(updatedItem)
        }.orElseThrow { RuntimeException("Item not found") }
    }

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Long) = repository.deleteById(id)
}
