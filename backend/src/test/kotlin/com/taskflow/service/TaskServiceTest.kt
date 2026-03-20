package com.taskflow.service

import com.taskflow.models.Task
import com.taskflow.repository.TaskRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import kotlin.test.*

@MicronautTest
class TaskServiceTest {

    private val taskRepository: TaskRepository = mockk()
    private val taskService = TaskService(taskRepository)

    @Test
    fun `findAll should return all tasks`() {
        val tasks = listOf(Task(id = 1, title = "Task 1"), Task(id = 2, title = "Task 2"))
        every { taskRepository.findAll() } returns tasks

        val result = taskService.findAll()

        assertEquals(tasks, result)
        verify(exactly = 1) { taskRepository.findAll() }
    }

    @Test
    fun `findById should return task when exists`() {
        val task = Task(id = 1, title = "Task 1")
        every { taskRepository.findById(1L) } returns Optional.of(task)

        val result = taskService.findById(1L)

        assertNotNull(result)
        assertEquals(task, result)
        verify(exactly = 1) { taskRepository.findById(1L) }
    }

    @Test
    fun `findById should return null when not exists`() {
        every { taskRepository.findById(1L) } returns Optional.empty()

        val result = taskService.findById(1L)

        assertNull(result)
        verify(exactly = 1) { taskRepository.findById(1L) }
    }

    @Test
    fun `save should set createdAt and updatedAt for new task`() {
        val task = Task(title = "New Task")
        val savedTask = task.copy(id = 1L)
        every { taskRepository.save(any()) } returns savedTask

        val result = taskService.save(task)

        assertNotNull(result.id)
        verify(exactly = 1) { taskRepository.save(any()) }
    }

    @Test
    fun `save should only update updatedAt for existing task`() {
        val task = Task(id = 1L, title = "Existing Task")
        val savedTask = task.copy()
        every { taskRepository.save(any()) } returns savedTask

        val result = taskService.save(task)

        assertEquals(1L, result.id)
        verify(exactly = 1) { taskRepository.save(any()) }
    }

    @Test
    fun `delete should call repository deleteById`() {
        every { taskRepository.deleteById(1L) } returns Unit

        taskService.delete(1L)

        verify(exactly = 1) { taskRepository.deleteById(1L) }
    }
}
