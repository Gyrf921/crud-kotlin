package com.example.crud.service.impl

import com.example.crud.dao.User
import com.example.crud.repository.UserRepository
import com.example.crud.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
): UserService {

    override fun existsById(id: UUID): Boolean =
        userRepository.findById(id).isPresent


    override fun getUserById(id: UUID): User? =
        userRepository.findById(id).orElse(null)


    override fun getAllUsers(): List<User> =
        userRepository.findAll()


    override fun createUser(user: User): User =
        userRepository.save(user)


    override fun updateUser(id: UUID, user: User): User? {
        val existingUser = getUserById(id)

        if (existingUser == null) return null

        val updatedUser = existingUser.copy(name = user.name!!, email = user.email!!)

        return userRepository.save(updatedUser)
    }

    override fun deleteUserById(id: UUID) =
        userRepository.deleteById(id)

}