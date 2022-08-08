package com.bloxes.userdb.model

import java.util.*

data class UpdateUserRequest (
    var password: String,
    var user_name: String,
    var subscribed_space: Number,
    var used_space: Number,
    var recycle_bin: MutableList<String>,
)