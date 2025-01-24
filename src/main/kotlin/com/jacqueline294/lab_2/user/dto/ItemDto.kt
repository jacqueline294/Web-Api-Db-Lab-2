package com.jacqueline294.lab_2.user.dto

import jakarta.validation.constraints.NotBlank

data class ItemDto(
    @field:NotBlank(message = "Item name cannot be blank")
    val itemName: String,

    val quantity: Int,

    val userId: Long
)
