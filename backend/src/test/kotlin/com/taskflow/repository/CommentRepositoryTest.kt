package com.taskflow.repository

import com.taskflow.models.Comment
import com.taskflow.models.Project
import com.taskflow.models.Task
import com.taskflow.models.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CommentRepositoryTest : BaseRepositoryTest() {

    private lateinit var testUser: User
    private lateinit var testProject: Project
    private lateinit var testTask: Task

    @BeforeEach
    fun setup() {
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
