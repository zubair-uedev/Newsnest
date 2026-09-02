package com.example.newsnest.utils

sealed class NetWorkResult<T>(val data:T? = null, val message: String? = null){
    class Success<T>(data:T): NetWorkResult<T>()
    class Error<T>(message: String?,data:T? = null): NetWorkResult<T>(data,message)
    class Loading<T>: NetWorkResult<T>()
    class Idle<T>: NetWorkResult<T>()
}