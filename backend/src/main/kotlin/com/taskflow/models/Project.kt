package com.taskflow.models

data class Project(
    val id: Long,
    val name: String,
    val description: String,
    val ownerId: Long,
    val color: String,
    val createdAt: String,
    val updatedAt: String
)