package com.taskflow.repository

import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.BeforeEach

@MicronautTest
open class BaseRepositoryTest {

    @Inject lateinit var projectRepository: ProjectRepository
    @Inject lateinit var userRepository: UserRepository
    @Inject lateinit var projectMemberRepository: ProjectMemberRepository
    @Inject lateinit var taskRepository: TaskRepository
    @Inject lateinit var commentRepository: CommentRepository
    @Inject lateinit var attachmentRepository: AttachmentRepository
    @Inject lateinit var taskActivityLogRepository: TaskActivityLogRepository
    @Inject lateinit var labelRepository: LabelRepository

    @BeforeEach
    fun setupBase() {
        // Delete in order to respect foreign key constraints
        attachmentRepository.deleteAll()
        commentRepository.deleteAll()
        taskActivityLogRepository.deleteAll()
        taskRepository.deleteAll()
        projectMemberRepository.deleteAll()
        projectRepository.deleteAll()
        labelRepository.deleteAll()
        userRepository.deleteAll()
    }
}
