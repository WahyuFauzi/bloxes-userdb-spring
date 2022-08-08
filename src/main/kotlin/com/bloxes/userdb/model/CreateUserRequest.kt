package com.bloxes.userdb.model

data class CreateUserRequest (
    val email: String,
    val password: String,
    val user_name: String
)