package com.taskflow.repository

import com.taskflow.models.Project
import com.taskflow.models.ProjectMember
import com.taskflow.models.User
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest
class ProjectMemberRepositoryTest {

    @Inject lateinit var projectMemberRepository: ProjectMemberRepository
    @Inject lateinit var projectRepository: ProjectRepository
    @Inject lateinit var userRepository: UserRepository
    @Inject lateinit var taskRepository: TaskRepository
    @Inject lateinit var commentRepository: CommentRepository
    @Inject lateinit var attachmentRepository: AttachmentRepository
    @Inject lateinit var taskActivityLogRepository: TaskActivityLogRepository
    @Inject lateinit var labelRepository: LabelRepository

    private lateinit var testUser: User
    private lateinit var testProject: Project

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

        val uniqueUsername = "member_${System.nanoTime()}"
        val uniqueEmail = "member_${System.nanoTime()}@example.com"
        testUser = userRepository.save(User(username = uniqueUsername, email = uniqueEmail, password = "p"))
        testProject = projectRepository.save(Project(name = "P1", owner = testUser))
    }

    @Test
    fun `should save and find member`() {
        val member = projectMemberRepository.save(ProjectMember(project = testProject, user = testUser, role = "CONTRIBUTOR"))
        assertNotNull(member.id)
        val found = projectMemberRepository.findById(member.id!!).get()
        assertEquals("CONTRIBUTOR", found.role)
    }
}
