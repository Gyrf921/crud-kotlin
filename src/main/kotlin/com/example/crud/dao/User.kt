package com.example.crud.dao

import jakarta.persistence.*
import org.hibernate.annotations.UuidGenerator
import java.util.*

@Entity
@Table(name = "user_crud")
class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @UuidGenerator
    @Column(name = "id")
    var id: UUID? = null

    @Column(name = "name", nullable = false)
    var name: String? = null

    @Column(name = "email", nullable = false)
    var email: String? = null

    fun copy(name: String, email: String): User{
        this.name = name
        this.email = email
        return this
    }
}