package com.taskflow.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "task_activity_log")
data class TaskActivityLog(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    var task: Task? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User? = null,

    @Column(nullable = false)
    var action: String = "",

    @Column(name = "old_value", columnDefinition = "TEXT")
    var oldValue: String? = null,

    @Column(name = "new_value", columnDefinition = "TEXT")
    var newValue: String? = null,

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
)
