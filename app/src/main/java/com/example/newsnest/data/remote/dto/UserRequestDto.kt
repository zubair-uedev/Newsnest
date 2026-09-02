package com.example.newsnest.data.remote.dto

import com.example.newsnest.domain.model.UserRequest

data class UserRequestDto(
    val email: String,
    val password: String,
    val username: String
)


fun UserRequestDto.toDomain(): UserRequest{
    return UserRequest(
        email = this.email,
        password = this.password,
        username = this.username
    )
}
fun UserRequest.toDto() : UserRequestDto {
    return UserRequestDto(
        email = this.email,
        password = this.password,
        username = this.username
    )
}