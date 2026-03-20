package com.taskflow.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "comments")
data class Comment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    var task: Task? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,

    @Column(columnDefinition = "TEXT", nullable = false)
    var content: String,

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
)
