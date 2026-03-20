package com.taskflow.service

import com.taskflow.models.TaskActivityLog
import com.taskflow.repository.TaskActivityLogRepository
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class TaskActivityLogService(private val taskActivityLogRepository: TaskActivityLogRepository) {

    fun findAll(): Iterable<TaskActivityLog> = taskActivityLogRepository.findAll()

    fun findById(id: Long): TaskActivityLog? = taskActivityLogRepository.findById(id).orElse(null)

    fun save(taskActivityLog: TaskActivityLog): TaskActivityLog {
        if (taskActivityLog.id == null) {
            taskActivityLog.createdAt = LocalDateTime.now()
        }
        return taskActivityLogRepository.save(taskActivityLog)
    }

    fun delete(id: Long) {
        taskActivityLogRepository.deleteById(id)
    }
}
