package com.bloxes.userdb.helper

import com.bloxes.userdb.entity.User
import com.bloxes.userdb.model.UserResponse
import org.springframework.stereotype.Component

@Component
class Helper {

    fun entityToResponse(entity: User): UserResponse {
        return UserResponse(
            id = entity.id,
            email = entity.email,
            password = entity.password,
            user_name = entity.user_name,
            subscribed_space = entity.subscribed_space,
            used_space = entity.used_space,
            subscribed_at = entity.subscribed_at,
            end_of_subscription = entity.end_of_subscription,
            init_folder = entity.init_folder,
            recycle_bin = entity.recycle_bin,
            created_at = entity.created_at,
            updated_at = entity.updated_at
        )
    }
}