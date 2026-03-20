package com.taskflow.repository

import com.taskflow.models.Project
import com.taskflow.models.Task
import com.taskflow.models.TaskActivityLog
import com.taskflow.models.User
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest
class TaskActivityLogRepositoryTest {

    @Inject lateinit var taskActivityLogRepository: TaskActivityLogRepository
    @Inject lateinit var taskRepository: TaskRepository
    @Inject lateinit var projectRepository: ProjectRepository
    @Inject lateinit var userRepository: UserRepository

    private lateinit var testUser: User
    private lateinit var testProject: Project
    private lateinit var testTask: Task

    @BeforeEach
    fun setup() {
        taskActivityLogRepository.deleteAll()
        taskRepository.deleteAll()
        projectRepository.deleteAll()
        userRepository.deleteAll()

        val uniqueEmail = "actor_${System.nanoTime()}@e.com"
        testUser = userRepository.save(User(username = "actor_${System.nanoTime()}", email = uniqueEmail, password = "p"))
        testProject = projectRepository.save(Project(name = "P1", owner = testUser))
        testTask = taskRepository.save(Task(title = "T1", project = testProject))
    }

    @Test
    fun `should save and find log`() {
        val log = taskActivityLogRepository.save(TaskActivityLog(action = "UPDATE_STATUS", task = testTask, user = testUser))
        assertNotNull(log.id)
        val found = taskActivityLogRepository.findById(log.id!!).get()
        assertEquals("UPDATE_STATUS", found.action)
    }
}
