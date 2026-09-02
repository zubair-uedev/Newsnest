package com.example.newsnest.presentation.auth.authviewmodel
import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsnest.domain.model.UserRequest
import com.example.newsnest.domain.model.UserResponse
import com.example.newsnest.domain.repo.UserRepo
import com.example.newsnest.utils.NetWorkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val userRepository: UserRepo
) : ViewModel() {
    //val userResponseLiveData: LiveData<NetWorkResult<UserResponseDto>>
    private val _userResponse = MutableStateFlow<NetWorkResult<UserResponse?>>(NetWorkResult.Idle())
    val userResponse = _userResponse.asStateFlow()

    fun signup(userRequest: UserRequest) {
        viewModelScope.launch {
            _userResponse.value = NetWorkResult.Loading()
            val response = userRepository.signUp(userRequest)
            _userResponse.value = response
        }
    }

    fun signin(userRequest: UserRequest) {
        viewModelScope.launch {
            _userResponse.value = NetWorkResult.Loading()
            val response = userRepository.signIn(userRequest)
            _userResponse.value = response
        }
    }


    //validdate k li ay fn
    fun validateCredentials(
        username: String,
        emailAddress: String,
        password: String,
        isLogin: Boolean
    ): Pair<Boolean, String> {
        var result = Pair(true, "")
        if ((!isLogin && TextUtils.isEmpty(username)) || TextUtils.isEmpty(emailAddress) || TextUtils.isEmpty(
                password
            )
        ) {
            result = Pair(false, "please provide the credentials")
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
            result = Pair(false, "please provide valid email")
        } else if (password.length <= 5) {
            result = Pair(false, "password should b greater then five")
        }
        return result
    }

}