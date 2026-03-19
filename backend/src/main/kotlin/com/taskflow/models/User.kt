package com.taskflow.models

import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val username: String,
    val email: String,
    val passwordHash: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
