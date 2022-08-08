package com.bloxes.userdb.model

import java.util.*

data class UserResponse (
    val id: String,
    val email: String,
    val password: String,
    val user_name: String,
    val subscribed_space: Number,
    val used_space: Number,
    val subscribed_at: String?,
    val end_of_subscription: String?,
    val init_folder: String,
    val recycle_bin: MutableList<String>,
    val created_at: Date,
    val updated_at: Date
)