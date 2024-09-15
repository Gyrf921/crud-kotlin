package com.example.crud.web.controller

import com.example.crud.dao.User
import com.example.crud.repository.UserRepository
import com.example.crud.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/users")
class UserController @Autowired constructor(private val userService: UserService) {

    @GetMapping("")
    fun getAllUsers(): List<User> =
        userService.getAllUsers()

    @PostMapping("")
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        val createdUser = userService.createUser(user)

        return ResponseEntity(createdUser, HttpStatus.CREATED)
    }

    @GetMapping("/{id}")
    fun getUserById(@PathVariable("id") userId: String): ResponseEntity<User> {
        val user = userService.getUserById(UUID.fromString(userId))
        return if (user != null)
            ResponseEntity(user, HttpStatus.OK)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PutMapping("/{id}")
    fun updateUserById(@PathVariable("id") userId: String, @RequestBody user: User): ResponseEntity<User> {

        val updatedUser = userService.updateUser(UUID.fromString(userId), user)

        if (updatedUser == null) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }

        return ResponseEntity(updatedUser, HttpStatus.OK)
    }

    @DeleteMapping("/{id}")
    fun deleteUserById(@PathVariable("id") userId: String): ResponseEntity<User> {
        if (!userService.existsById(UUID.fromString(userId))) {
            return ResponseEntity(HttpStatus.NOT_FOUND)
        }
        userService.deleteUserById(UUID.fromString(userId))
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}