package com.example.newsnest.data.remote.dto

import com.example.newsnest.domain.model.User

data class UserDto(
    val __v: Int,
    val _id: String,
    val createdAt: String,
    val email: String,
    val password: String,
    val updatedAt: String,
    val username: String
)

fun UserDto.toDomain(): User {
    return User(
        __v = this.__v,
        _id = this._id,
        createdAt = this.createdAt,
        email = this.email,
        password = this.password,
        updatedAt = this.updatedAt,
        username = this.username
    )
}

fun User.toDto(): UserDto {
    return UserDto(
        __v =this.__v,
        _id = this._id,
        createdAt = this.createdAt,
        email = this.email,
        password = this.password,
        updatedAt = this.updatedAt,
        username = this.username
    )
}