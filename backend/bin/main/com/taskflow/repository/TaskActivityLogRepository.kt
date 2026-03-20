package com.taskflow.repository

import com.taskflow.models.TaskActivityLog
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface TaskActivityLogRepository : JpaRepository<TaskActivityLog, Long>
