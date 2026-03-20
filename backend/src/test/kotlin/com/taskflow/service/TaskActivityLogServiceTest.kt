package com.taskflow.service

import com.taskflow.models.TaskActivityLog
import com.taskflow.repository.TaskActivityLogRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import kotlin.test.*

@MicronautTest
class TaskActivityLogServiceTest {

    private val taskActivityLogRepository: TaskActivityLogRepository = mockk()
    private val taskActivityLogService = TaskActivityLogService(taskActivityLogRepository)

    @Test
    fun `findAll should return all logs`() {
        val logs = listOf(TaskActivityLog(id = 1, action = "update"), TaskActivityLog(id = 2, action = "create"))
        every { taskActivityLogRepository.findAll() } returns logs

        val result = taskActivityLogService.findAll()

        assertEquals(logs, result)
        verify(exactly = 1) { taskActivityLogRepository.findAll() }
    }

    @Test
    fun `findById should return log when exists`() {
        val log = TaskActivityLog(id = 1, action = "update")
        every { taskActivityLogRepository.findById(1L) } returns Optional.of(log)

        val result = taskActivityLogService.findById(1L)

        assertNotNull(result)
        assertEquals(log, result)
        verify(exactly = 1) { taskActivityLogRepository.findById(1L) }
    }

    @Test
    fun `findById should return null when not exists`() {
        every { taskActivityLogRepository.findById(1L) } returns Optional.empty()

        val result = taskActivityLogService.findById(1L)

        assertNull(result)
        verify(exactly = 1) { taskActivityLogRepository.findById(1L) }
    }

    @Test
    fun `save should set createdAt for new log`() {
        val log = TaskActivityLog(action = "new")
        val savedLog = log.copy(id = 1L)
        every { taskActivityLogRepository.save(any()) } returns savedLog

        val result = taskActivityLogService.save(log)

        assertNotNull(result.id)
        verify(exactly = 1) { taskActivityLogRepository.save(any()) }
    }

    @Test
    fun `delete should call repository deleteById`() {
        every { taskActivityLogRepository.deleteById(1L) } returns Unit

        taskActivityLogService.delete(1L)

        verify(exactly = 1) { taskActivityLogRepository.deleteById(1L) }
    }
}
