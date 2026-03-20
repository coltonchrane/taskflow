package com.taskflow.service

import com.taskflow.models.ProjectMember
import com.taskflow.repository.ProjectMemberRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import kotlin.test.*

@MicronautTest
class ProjectMemberServiceTest {

    private val projectMemberRepository: ProjectMemberRepository = mockk()
    private val projectMemberService = ProjectMemberService(projectMemberRepository)

    @Test
    fun `findAll should return all project members`() {
        val members = listOf(ProjectMember(id = 1, role = "admin"), ProjectMember(id = 2, role = "member"))
        every { projectMemberRepository.findAll() } returns members

        val result = projectMemberService.findAll()

        assertEquals(members, result)
        verify(exactly = 1) { projectMemberRepository.findAll() }
    }

    @Test
    fun `findById should return project member when exists`() {
        val member = ProjectMember(id = 1, role = "admin")
        every { projectMemberRepository.findById(1L) } returns Optional.of(member)

        val result = projectMemberService.findById(1L)

        assertNotNull(result)
        assertEquals(member, result)
        verify(exactly = 1) { projectMemberRepository.findById(1L) }
    }

    @Test
    fun `save should set createdAt for new project member`() {
        val member = ProjectMember(role = "admin")
        val savedMember = member.copy(id = 1L)
        every { projectMemberRepository.save(any()) } returns savedMember

        val result = projectMemberService.save(member)

        assertNotNull(result.id)
        verify(exactly = 1) { projectMemberRepository.save(any()) }
    }

    @Test
    fun `delete should call repository deleteById`() {
        every { projectMemberRepository.deleteById(1L) } returns Unit

        projectMemberService.delete(1L)

        verify(exactly = 1) { projectMemberRepository.deleteById(1L) }
    }
}
