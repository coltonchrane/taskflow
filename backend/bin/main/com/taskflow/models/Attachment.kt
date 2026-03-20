package com.taskflow.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "attachments")
data class Attachment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", nullable = false)
    var task: Task? = null,

    @Column(name = "file_path", nullable = false)
    var filePath: String,

    @Column(name = "uploaded_at", updatable = false)
    var uploadedAt: LocalDateTime = LocalDateTime.now()
)
