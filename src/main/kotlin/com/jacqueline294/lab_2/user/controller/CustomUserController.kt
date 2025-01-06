package com.jacqueline294.lab_2.user.controller


import com.jacqueline294.lab_2.config.requests.LoginRequest
import com.jacqueline294.lab_2.config.requests.RegisterRequest
import com.jacqueline294.lab_2.config.services.UserService
import com.jacqueline294.lab_2.user.model.CustomUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*




@RestController
@RequestMapping("/api/users")
class CustomUserController(@Autowired private val userService: UserService) {

    @PostMapping("/register")
    fun registerUser(@RequestBody request: RegisterRequest): ResponseEntity<CustomUser> {
        val user = userService.registerUser(request.username, request.password)
        return ResponseEntity.status(HttpStatus.CREATED).body(user)
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody request: LoginRequest): ResponseEntity<CustomUser> {
        val user = userService.authenticateUser(request.username, request.password)
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        return ResponseEntity.ok(user)
    }


    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
        return if (userService.deleteUser(id)) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}





