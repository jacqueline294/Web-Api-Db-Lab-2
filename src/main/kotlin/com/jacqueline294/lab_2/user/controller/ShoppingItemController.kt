package com.jacqueline294.lab_2.user.controller

import com.jacqueline294.lab_2.config.services.ShoppingItemService
import com.jacqueline294.lab_2.user.model.ShoppingItem
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/items")
class ShoppingItemController(@Autowired private val service: ShoppingItemService) {

    @GetMapping
    fun getAllItems(@RequestParam userId: Long): ResponseEntity<List<ShoppingItem>> =
        ResponseEntity.ok(service.getAllItems(userId))

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: Long, @RequestParam userId: Long): ResponseEntity<ShoppingItem> {
        val item = service.getItemById(id, userId) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(item)
    }

    @PostMapping
    fun addItem(@RequestBody item: ShoppingItem): ResponseEntity<ShoppingItem> =
        ResponseEntity.status(HttpStatus.CREATED).body(service.addItem(item))

    @PutMapping("/{id}")
    fun updateItem(@PathVariable id: Long, @RequestParam userId: Long, @RequestBody updatedItem: ShoppingItem): ResponseEntity<ShoppingItem> {
        val item = service.updateItem(id, userId, updatedItem) ?: return ResponseEntity(HttpStatus.NOT_FOUND)
        return ResponseEntity.ok(item)
    }

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Long, @RequestParam userId: Long): ResponseEntity<Void> {
        return if (service.deleteItem(id, userId)) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
