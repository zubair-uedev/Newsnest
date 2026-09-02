package com.example.newsnest.data.remote.dto

import com.example.newsnest.domain.model.UserResponse

data class UserResponseDto(
    val token: String,
    val user: UserDto
)

fun UserResponseDto.toDomain(): UserResponse{
    return UserResponse(
        token = this.token,
        user = this.user.toDomain()
    )
}
fun UserResponse.toDto(): UserResponseDto{
    return UserResponseDto(
        token = this.token,
        user = this.user.toDto()
    )
}
