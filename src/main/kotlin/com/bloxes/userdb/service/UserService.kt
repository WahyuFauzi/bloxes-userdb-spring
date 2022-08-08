package com.bloxes.userdb.service

import com.bloxes.userdb.model.CreateUserRequest
import com.bloxes.userdb.model.UpdateUserRequest
import com.bloxes.userdb.model.UserResponse

interface UserService {

    fun createUser(createUserRequest: CreateUserRequest)

    fun getUser(userId: String): UserResponse

    fun updateUser(updateUserRequest: UpdateUserRequest, userId: String)

    fun deleteUser(userId: String)
}
