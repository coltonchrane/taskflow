package com.taskflow.models

import java.time.LocalDateTime

data class Project(
    val id: Long? = null,
    val name: String,
    val description: String? = null,
    val ownerId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)
