package com.taskflow.controller

import com.taskflow.models.Task
import com.taskflow.service.TaskService
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.*

@MicronautTest
class TaskControllerTest {

    private val taskService: TaskService = mockk()
    private val taskController = TaskController(taskService)

    @Test
    fun `listTasks should return all tasks`() {
        val tasks = listOf(Task(id = 1, title = "Task 1"), Task(id = 2, title = "Task 2"))
        every { taskService.findAll() } returns tasks

        val result = taskController.listTasks()

        assertEquals(tasks, result.toList())
        verify(exactly = 1) { taskService.findAll() }
    }

    @Test
    fun `getTask should return task when found`() {
        val task = Task(id = 1, title = "Task 1")
        every { taskService.findById(1L) } returns task

        val response = taskController.getTask(1L)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(task, response.body())
        verify(exactly = 1) { taskService.findById(1L) }
    }

    @Test
    fun `getTask should return 404 when not found`() {
        every { taskService.findById(1L) } returns null

        val response = taskController.getTask(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        verify(exactly = 1) { taskService.findById(1L) }
    }

    @Test
    fun `createTask should return 201 created`() {
        val task = Task(title = "New Task")
        val savedTask = task.copy(id = 1L)
        every { taskService.save(any()) } returns savedTask

        val response = taskController.createTask(task)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals(savedTask, response.body())
        verify(exactly = 1) { taskService.save(any()) }
    }

    @Test
    fun `deleteTask should return 204 no content`() {
        every { taskService.delete(1L) } returns Unit

        val response = taskController.deleteTask(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.status)
        verify(exactly = 1) { taskService.delete(1L) }
    }
}
