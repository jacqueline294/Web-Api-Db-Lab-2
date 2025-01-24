package com.jacqueline294.lab_2.user.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.data.jpa.repository.JpaRepository

@Entity
data class ShoppingItem(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var itemName: String,

    @Column(nullable = false)
    var quantity: Int,

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    val user: CustomUser
)

