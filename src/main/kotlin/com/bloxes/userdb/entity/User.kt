package com.bloxes.userdb.entity

import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "user")
data class User (
        val id: String,
        val email: String,
        var password: String,
        var user_name: String,
        var subscribed_space: Number,
        var used_space: Number,
        var subscribed_at: String?,
        var end_of_subscription: String?,
        val init_folder: String,
        var recycle_bin: MutableList<String>,
        var pinned: MutableList<String>,
        var recent: MutableList<String>,
        val created_at: String,
        var updated_at: String
        )