package com.taskflow.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    var project: Project? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_to")
    var assignedTo: User? = null,

    @Column(nullable = false)
    var title: String = "",

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    @Column(nullable = false)
    var status: String = "pending",

    @Column(nullable = false)
    var priority: Int = 0,

    @Column(name = "due_date")
    var dueDate: LocalDateTime? = null,

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL], orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf(),

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL], orphanRemoval = true)
    var attachments: MutableList<Attachment> = mutableListOf(),

    @ManyToMany
    @JoinTable(
        name = "task_labels",
        joinColumns = [JoinColumn(name = "task_id")],
        inverseJoinColumns = [JoinColumn(name = "label_id")]
    )
    var labels: MutableSet<Label> = mutableSetOf()
)
