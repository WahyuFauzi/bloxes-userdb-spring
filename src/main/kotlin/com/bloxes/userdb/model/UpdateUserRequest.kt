package com.bloxes.userdb.model

import java.util.*

data class UpdateUserRequest (
    val password: String,
    val user_name: String,
    val subscribed_space: Number,
    val used_space: Number,
    val recycle_bin: MutableList<String>,
    val pinned: MutableList<String>,
    val recent: MutableList<String>
)