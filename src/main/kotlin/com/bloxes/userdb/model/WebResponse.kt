package com.bloxes.userdb.model

data class WebResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)