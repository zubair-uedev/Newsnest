package com.example.newsnest.data.remote.repository

import com.example.newsnest.data.remote.api.UserApi
import com.example.newsnest.data.remote.dto.UserResponseDto
import com.example.newsnest.data.remote.dto.toDomain
import com.example.newsnest.data.remote.dto.toDto
import com.example.newsnest.domain.model.UserRequest
import com.example.newsnest.domain.model.UserResponse
import com.example.newsnest.domain.repo.UserRepo
import com.example.newsnest.utils.NetWorkResult
import org.json.JSONObject
import retrofit2.Response
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userApi: UserApi
) : UserRepo{

    override suspend fun signUp(userRequest: UserRequest): NetWorkResult<UserResponse?> {
        val response = userApi.signUp(userRequest.toDto())

        return handleResponse(response)
     }

    override suspend fun signIn(userRequest: UserRequest): NetWorkResult<UserResponse?> {
        val response = userApi.signIn(userRequest.toDto())

        return handleResponse(response)
     }

    private fun handleResponse(response: Response<UserResponseDto>): NetWorkResult<UserResponse?> {
        if (response.isSuccessful && response.body() != null) {
            return NetWorkResult.Success(response.body()?.toDomain())
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            return NetWorkResult.Error(errorObj.getString("message"))
         } else {
             return NetWorkResult.Error("something want error")
         }
    }

}


