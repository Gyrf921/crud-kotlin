package com.example.crud.service

import com.example.crud.dao.User
import org.springframework.stereotype.Service
import java.util.*

@Service
interface UserService {

    fun existsById(id: UUID): Boolean

    fun getUserById(id: UUID): User?

    fun getAllUsers(): List<User>

    fun createUser(user: User): User

    fun updateUser(id: UUID, user: User): User?

    fun deleteUserById(id: UUID)

}