package com.example.newsnest.data.remote.api
import com.example.newsnest.data.remote.dto.UserRequestDto
import com.example.newsnest.data.remote.dto.UserResponseDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    @POST("/users/signup")
    suspend fun signUp(@Body userRequest: UserRequestDto): Response<UserResponseDto>
    @POST("/users/signin")
    suspend fun signIn(@Body userRequest: UserRequestDto): Response<UserResponseDto>
}