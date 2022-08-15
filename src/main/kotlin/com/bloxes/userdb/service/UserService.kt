package com.bloxes.userdb.service

import com.bloxes.userdb.model.CreateUserRequest
import com.bloxes.userdb.model.UpdateUserRequest
import com.bloxes.userdb.model.UserResponse

interface UserService {

    fun createUser(createUserRequest: CreateUserRequest): UserResponse

    fun getUser(userId: String): UserResponse

    fun updateUser(userId: String, updateUserRequest: UpdateUserRequest): UserResponse

    fun deleteUser(userId: String)
}
