package com.taskflow.controller

import com.taskflow.models.TaskActivityLog
import com.taskflow.service.TaskActivityLogService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/task-activity-logs")
class TaskActivityLogController(private val taskActivityLogService: TaskActivityLogService) {

    @Get("/")
    fun listTaskActivityLogs(): Iterable<TaskActivityLog> = taskActivityLogService.findAll()

    @Get("/{id}")
    fun getTaskActivityLog(id: Long): HttpResponse<TaskActivityLog> {
        val log = taskActivityLogService.findById(id)
        return if (log != null) {
            HttpResponse.ok(log)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post("/")
    fun createTaskActivityLog(@Body log: TaskActivityLog): HttpResponse<TaskActivityLog> {
        val savedLog = taskActivityLogService.save(log)
        return HttpResponse.created(savedLog)
    }

    @Delete("/{id}")
    fun deleteTaskActivityLog(id: Long): HttpResponse<Unit> {
        taskActivityLogService.delete(id)
        return HttpResponse.noContent()
    }
}
