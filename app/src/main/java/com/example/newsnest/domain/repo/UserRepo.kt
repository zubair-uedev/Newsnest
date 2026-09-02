package com.example.newsnest.domain.repo

import com.example.newsnest.domain.model.UserRequest
import com.example.newsnest.domain.model.UserResponse
import com.example.newsnest.utils.NetWorkResult

interface UserRepo {
    suspend fun signUp(userRequest: UserRequest): NetWorkResult<UserResponse?>
    suspend fun signIn(userRequest: UserRequest): NetWorkResult<UserResponse?>
}