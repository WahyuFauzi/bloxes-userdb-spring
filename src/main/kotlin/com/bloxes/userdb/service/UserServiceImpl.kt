package com.bloxes.userdb.service

import com.bloxes.userdb.entity.User
import com.bloxes.userdb.helper.Helper
import com.bloxes.userdb.model.CreateUserRequest
import com.bloxes.userdb.model.UpdateUserRequest
import com.bloxes.userdb.model.UserResponse
import com.bloxes.userdb.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(val userRepository: UserRepository, val helper: Helper): UserService {
    override fun createUser(createUserRequest: CreateUserRequest): UserResponse {
        val entity = User(
            id = UUID.randomUUID().toString(),
            email = createUserRequest.email,
            password = createUserRequest.password,
            user_name = createUserRequest.user_name,
            subscribed_space = 0,
            used_space = 0,
            subscribed_at = null,
            end_of_subscription = null,
            init_folder = "init_folder",
            recycle_bin = mutableListOf(),
            pinned = mutableListOf(),
            recent = mutableListOf(),
            created_at = Date(),
            updated_at = Date()
        )

        userRepository.save(entity)

        return helper.entityToResponse(entity)
    }

    override fun getUser(userId: String): UserResponse {
        val entity = userRepository.findByIdOrNull(userId)
        return helper.entityToResponse(entity!!)
    }

    override fun updateUser(updateUserRequest: UpdateUserRequest, userId: String): UserResponse {
        val entity = userRepository.findById(userId).get()

        entity.apply {
            password = updateUserRequest.password
            user_name = updateUserRequest.user_name
            subscribed_space = updateUserRequest.subscribed_space
            used_space = updateUserRequest.used_space
            recycle_bin = updateUserRequest.recycle_bin
            pinned = updateUserRequest.pinned
            recent = updateUserRequest.recent
        }

        userRepository.save(entity)

        return helper.entityToResponse(entity)
    }

    override fun deleteUser(userId: String) {
        userRepository.deleteById(userId)
    }
}