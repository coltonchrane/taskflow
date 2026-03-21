package com.taskflow.repository

import com.taskflow.models.User
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest
class UserRepositoryTest {

    @Inject
    lateinit var userRepository: UserRepository

    @Inject lateinit var projectRepository: ProjectRepository
    @Inject lateinit var projectMemberRepository: ProjectMemberRepository
    @Inject lateinit var taskRepository: TaskRepository
    @Inject lateinit var commentRepository: CommentRepository
    @Inject lateinit var attachmentRepository: AttachmentRepository
    @Inject lateinit var taskActivityLogRepository: TaskActivityLogRepository
    @Inject lateinit var labelRepository: LabelRepository

    @BeforeEach
    fun setup() {
        attachmentRepository.deleteAll()
        commentRepository.deleteAll()
        taskActivityLogRepository.deleteAll()
        taskRepository.deleteAll()
        projectMemberRepository.deleteAll()
        labelRepository.deleteAll()
        projectRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    fun `should save and find user`() {
        val user = User(username = "uniqueUser1", email = "unique1@example.com", password = "secretPassword")
        val savedUser = userRepository.save(user)

        assertNotNull(savedUser.id)
        
        val foundUser = userRepository.findById(savedUser.id!!).orElse(null)
        assertNotNull(foundUser)
        assertEquals("uniqueUser1", foundUser?.username)
    }

    @Test
    fun `should fail on duplicate username`() {
        userRepository.save(User(username = "duplicate", email = "unique2@example.com", password = "p"))
        
        assertThrows(Exception::class.java) {
            userRepository.save(User(username = "duplicate", email = "unique3@example.com", password = "p"))
        }
    }
}
