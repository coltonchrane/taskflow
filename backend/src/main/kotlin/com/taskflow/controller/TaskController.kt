package com.taskflow.controller

import com.taskflow.models.Task
import com.taskflow.service.TaskService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/tasks")
class TaskController(private val taskService: TaskService) {

    @Get("/")
    fun listTasks(): Iterable<Task> = taskService.findAll()

    @Get("/{id}")
    fun getTask(id: Long): HttpResponse<Task> {
        val task = taskService.findById(id)
        return if (task != null) {
            HttpResponse.ok(task)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post("/")
    fun createTask(@Body task: Task): HttpResponse<Task> {
        val savedTask = taskService.save(task)
        return HttpResponse.created(savedTask)
    }

    @Delete("/{id}")
    fun deleteTask(id: Long): HttpResponse<Unit> {
        taskService.delete(id)
        return HttpResponse.noContent()
    }
}
