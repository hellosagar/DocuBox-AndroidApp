package com.docubox.data.remote.dataSources

import com.docubox.data.modes.remote.UserDto
import com.docubox.data.modes.remote.requests.LoginRequest
import com.docubox.data.modes.remote.requests.RegisterRequest
import com.docubox.data.remote.api.AuthService
import com.docubox.util.Resource
import com.docubox.util.safeApiCall
import javax.inject.Inject

class AuthDataSource @Inject constructor(private val authService: AuthService) {

    suspend fun loginUser(
        email: String,
        password: String
    ): Resource<UserDto> = safeApiCall {
        authService.loginUser(LoginRequest(email, password))
    }

    suspend fun registerUser(
        username: String,
        email: String,
        password: String
    ): Resource<UserDto> = safeApiCall {
        authService.registerUser(RegisterRequest(email, username, password))
    }
}
