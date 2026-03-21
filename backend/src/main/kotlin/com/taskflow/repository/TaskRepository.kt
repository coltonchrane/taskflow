package com.taskflow.repository

import com.taskflow.models.Task
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface TaskRepository : JpaRepository<Task, Long> {
    fun findByProjectId(projectId: Long): Iterable<Task>
}
