package com.taskflow.service

import com.taskflow.models.Task
import com.taskflow.repository.TaskRepository
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class TaskService(private val taskRepository: TaskRepository) {

    fun findAll(): Iterable<Task> = taskRepository.findAll()

    fun findByProjectId(projectId: Long): Iterable<Task> = taskRepository.findByProjectId(projectId)

    fun findById(id: Long): Task? = taskRepository.findById(id).orElse(null)

    fun save(task: Task): Task {
        val toSave = if (task.id == null) {
            task.copy(createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now())
        } else {
            task.copy(updatedAt = LocalDateTime.now())
        }
        return taskRepository.save(toSave)
    }

    fun delete(id: Long) {
        taskRepository.deleteById(id)
    }
}
