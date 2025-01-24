package com.jacqueline294.lab_2.user.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size


@Entity
class CustomUser(
    @field:NotEmpty
    @field:Size(min = 2, max = 16)
    var username: String,

    @field:NotEmpty
    @field:Size(min = 7, max = 73)
    var password: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
)
