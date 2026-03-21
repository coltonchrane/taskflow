package com.taskflow.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "labels")
data class Label(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false, unique = true)
    var name: String = "",

    @Column(length = 20)
    var color: String = "#000000",

    @Column(name = "created_at", updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @ManyToMany(mappedBy = "labels")
    @com.fasterxml.jackson.annotation.JsonIgnore
    var tasks: MutableSet<Task> = mutableSetOf()
)
