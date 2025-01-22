package com.jacqueline294.lab_2.user.controller

import com.jacqueline294.lab_2.user.model.CustomUser
import com.jacqueline294.lab_2.user.repository.CustomUserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class CustomUserController @Autowired constructor(
    val repository: CustomUserRepository,
    val passwordEncoder: PasswordEncoder
) {

    @GetMapping("/password")
    fun getBcryptPassword(): ResponseEntity<String> {
        val testPassword = "123"
        val encodedPassword = passwordEncoder.encode(testPassword)
        return ResponseEntity.ok(encodedPassword)
    }

    @GetMapping
    fun getAllUsers(): ResponseEntity<List<CustomUser>> = ResponseEntity.ok(repository.findAll())

    @PostMapping
    fun saveUser(@Validated @RequestBody newUser: CustomUser): ResponseEntity<String> {
        if (repository.existsByUsername(newUser.username)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists")
        }

        val bcryptUser = CustomUser(
            newUser.username,
            passwordEncoder.encode(newUser.password)
        )

        repository.save(bcryptUser)
        return ResponseEntity.status(HttpStatus.CREATED).body("User was successfully created")
    }

    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> =
        if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
}
