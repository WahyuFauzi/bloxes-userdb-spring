package com.bloxes.userdb.service

import com.bloxes.userdb.entity.User
import com.bloxes.userdb.helper.DateHelper
import com.bloxes.userdb.helper.Helper
import com.bloxes.userdb.helper.RepositoryHelper
import com.bloxes.userdb.helper.UUIDHelper
import com.bloxes.userdb.model.CreateUserRequest
import com.bloxes.userdb.model.UpdateUserRequest
import com.bloxes.userdb.model.UserResponse
import com.bloxes.userdb.repository.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val uuidHelper: UUIDHelper,
    val repoHelper: RepositoryHelper,
    val dateHelper: DateHelper,
    val helper: Helper
) : UserService {
    override fun createUser(createUserRequest: CreateUserRequest): UserResponse {
        val entity = User(
            id = uuidHelper.getRandomUUID(),
            email = createUserRequest.email,
            password = createUserRequest.password,
            user_name = createUserRequest.user_name,
            subscribed_space = 0,
            used_space = 0,
            subscribed_at = null,
            end_of_subscription = null,
            init_folder = "init_folder", // TODO change this after production
            recycle_bin = mutableListOf(),
            pinned = mutableListOf(),
            recent = mutableListOf(),
            created_at = dateHelper.getCurrentDateInString(),
            updated_at = dateHelper.getCurrentDateInString()
        )

        userRepository.save(entity)

        return helper.entityToResponse(entity)
    }

    override fun getUser(userId: String): UserResponse {
        val user = repoHelper.findProductByIdOrThrowNotFound(userId)
        println("founded user is $user")
        return helper.entityToResponse(user);
    }

    override fun updateUser(userId: String, updateUserRequest: UpdateUserRequest): UserResponse {
        val entity = repoHelper.findProductByIdOrThrowNotFound(userId)

        entity.apply {
            password = updateUserRequest.password
            user_name = updateUserRequest.user_name
            subscribed_space = updateUserRequest.subscribed_space
            used_space = updateUserRequest.used_space
            recycle_bin = updateUserRequest.recycle_bin
            pinned = updateUserRequest.pinned
            recent = updateUserRequest.recent
            updated_at = dateHelper.getCurrentDateInString()
        }

        userRepository.save(entity)

        return helper.entityToResponse(entity)
    }

    override fun deleteUser(userId: String) {
        userRepository.deleteById(userId)
    }
}