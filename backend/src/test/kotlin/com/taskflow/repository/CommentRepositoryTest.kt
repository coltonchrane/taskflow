package com.taskflow.repository

import com.taskflow.models.Comment
import com.taskflow.models.Project
import com.taskflow.models.Task
import com.taskflow.models.User
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest
class CommentRepositoryTest {

    @Inject lateinit var commentRepository: CommentRepository
    @Inject lateinit var taskRepository: TaskRepository
    @Inject lateinit var projectRepository: ProjectRepository
    @Inject lateinit var userRepository: UserRepository

    private lateinit var testUser: User
    private lateinit var testProject: Project
    private lateinit var testTask: Task

    @BeforeEach
    fun setup() {
        commentRepository.deleteAll()
        taskRepository.deleteAll()
        projectRepository.deleteAll()
        userRepository.deleteAll()

        val uniqueEmail = "commenter_${System.nanoTime()}@e.com"
        testUser = userRepository.save(User(username = "commenter_${System.nanoTime()}", email = uniqueEmail, password = "p"))
        testProject = projectRepository.save(Project(name = "P1", owner = testUser))
        testTask = taskRepository.save(Task(title = "T1", project = testProject))
    }

    @Test
    fun `should save and find comment`() {
        val comment = commentRepository.save(Comment(content = "Great task!", task = testTask, user = testUser))
        assertNotNull(comment.id)
        val found = commentRepository.findById(comment.id!!).get()
        assertEquals("Great task!", found.content)
    }
}
