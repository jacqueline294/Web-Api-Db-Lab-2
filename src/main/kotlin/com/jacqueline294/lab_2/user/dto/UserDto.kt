package com.jacqueline294.lab_2.user.dto

import jakarta.validation.constraints.NotBlank

data class UserDto(
    @field:NotBlank(message = "Username cannot be blank")
    val username: String,

    @field:NotBlank(message = "Password cannot be blank")
    val password: String
)

