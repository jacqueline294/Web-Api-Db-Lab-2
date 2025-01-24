package com.jacqueline294.lab_2.user.controller

import com.jacqueline294.lab_2.user.model.CustomUser
import com.jacqueline294.lab_2.user.repository.CustomUserRepository
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

        // Save
        customUserRepository.save(bcryptUser)

        return ResponseEntity.status(201).body("User was successfully created")
    }
    @PostMapping("/login")
    fun loginUser(@RequestBody loginRequest: Map<String, String>): ResponseEntity<Any> {
        val username = loginRequest["username"]
        val password = loginRequest["password"]

        if (username.isNullOrBlank() || password.isNullOrBlank()) {
            return ResponseEntity.badRequest().body("Username and password are required")
        }

        val user = customUserRepository.findAll().find { it.username == username }
            ?: return ResponseEntity.status(401).body("Invalid credentials")

        return if (passwordEncoder.matches(password, user.password)) {
            ResponseEntity.ok(mapOf("userId" to user.id, "message" to "Login successful"))
        } else {
            ResponseEntity.status(401).body("Invalid credentials")
        }
    }
}


