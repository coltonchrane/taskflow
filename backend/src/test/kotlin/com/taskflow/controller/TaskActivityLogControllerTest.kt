package com.taskflow.controller

import com.taskflow.models.TaskActivityLog
import com.taskflow.service.TaskActivityLogService
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.*

@MicronautTest
class TaskActivityLogControllerTest {

    private val taskActivityLogService: TaskActivityLogService = mockk()
    private val taskActivityLogController = TaskActivityLogController(taskActivityLogService)

    @Test
    fun `listTaskActivityLogs should return all logs`() {
        val logs = listOf(TaskActivityLog(id = 1, action = "update"), TaskActivityLog(id = 2, action = "create"))
        every { taskActivityLogService.findAll() } returns logs

        val result = taskActivityLogController.listTaskActivityLogs()

        assertEquals(logs, result.toList())
        verify(exactly = 1) { taskActivityLogService.findAll() }
    }

    @Test
    fun `getTaskActivityLog should return log when found`() {
        val log = TaskActivityLog(id = 1, action = "update")
        every { taskActivityLogService.findById(1L) } returns log

        val response = taskActivityLogController.getTaskActivityLog(1L)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(log, response.body())
        verify(exactly = 1) { taskActivityLogService.findById(1L) }
    }

    @Test
    fun `getTaskActivityLog should return 404 when not found`() {
        every { taskActivityLogService.findById(1L) } returns null

        val response = taskActivityLogController.getTaskActivityLog(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        verify(exactly = 1) { taskActivityLogService.findById(1L) }
    }

    @Test
    fun `createTaskActivityLog should return 201 created`() {
        val log = TaskActivityLog(action = "delete")
        val savedLog = log.copy(id = 1L)
        every { taskActivityLogService.save(any()) } returns savedLog

        val response = taskActivityLogController.createTaskActivityLog(log)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals(savedLog, response.body())
        verify(exactly = 1) { taskActivityLogService.save(any()) }
    }

    @Test
    fun `deleteTaskActivityLog should return 204 no content`() {
        every { taskActivityLogService.delete(1L) } returns Unit

        val response = taskActivityLogController.deleteTaskActivityLog(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.status)
        verify(exactly = 1) { taskActivityLogService.delete(1L) }
    }
}
