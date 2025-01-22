package com.jacqueline294.lab_2.user.controller

import com.jacqueline294.lab_2.user.model.CustomUser
import com.jacqueline294.lab_2.user.repository.CustomUserRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/user")
class CustomUserController @Autowired constructor(
    val customUserRepository: CustomUserRepository,
    val passwordEncoder: PasswordEncoder
) {

    @GetMapping("/password")
    fun getBcryptPassword(): String {
        val testPassword = "123"
        return passwordEncoder.encode(testPassword)
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<CustomUser>> {
        val users: List<CustomUser> = customUserRepository.findAll()
        return ResponseEntity.ok(users)
    }

    @PostMapping
    fun saveUser(
        @Validated @RequestBody newUser: CustomUser
    ): ResponseEntity<String> {

        val bcryptUser = CustomUser(
            newUser.username,
            passwordEncoder.encode(newUser.password)
        )

        customUserRepository.save(bcryptUser)

        return ResponseEntity.status(201).body("User was successfully created")
    }

}