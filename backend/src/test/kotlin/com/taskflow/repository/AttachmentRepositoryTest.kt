package com.taskflow.repository

import com.taskflow.models.Attachment
import com.taskflow.models.Project
import com.taskflow.models.Task
import com.taskflow.models.User
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest
class AttachmentRepositoryTest {

    @Inject lateinit var attachmentRepository: AttachmentRepository
    @Inject lateinit var taskRepository: TaskRepository
    @Inject lateinit var projectRepository: ProjectRepository
    @Inject lateinit var userRepository: UserRepository

    private lateinit var testUser: User
    private lateinit var testProject: Project
    private lateinit var testTask: Task

    @BeforeEach
    fun setup() {
        attachmentRepository.deleteAll()
        taskRepository.deleteAll()
        projectRepository.deleteAll()
        userRepository.deleteAll()

        val uniqueEmail = "uploader_${System.nanoTime()}@e.com"
        testUser = userRepository.save(User(username = "uploader_${System.nanoTime()}", email = uniqueEmail, password = "p"))
        testProject = projectRepository.save(Project(name = "P1", owner = testUser))
        testTask = taskRepository.save(Task(title = "T1", project = testProject))
    }

    @Test
    fun `should save and find attachment`() {
        val attachment = attachmentRepository.save(Attachment(filePath = "/path/to/file", task = testTask))
        assertNotNull(attachment.id)
        val found = attachmentRepository.findById(attachment.id!!).get()
        assertEquals("/path/to/file", found.filePath)
    }
}
