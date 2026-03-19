package com.taskflow.models

import java.time.LocalDateTime

data class Task(
    val id: String,
    val projectId: String,
    val title: String,
    val description: String,
    val status: String,
    val priority: String,
    val assignedTo: String,
    val createdBy: String,
    val dueDate: LocalDateTime,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
)