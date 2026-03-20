package com.taskflow.controller

import com.taskflow.models.ProjectMember
import com.taskflow.service.ProjectMemberService
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.*

@MicronautTest
class ProjectMemberControllerTest {

    private val projectMemberService: ProjectMemberService = mockk()
    private val projectMemberController = ProjectMemberController(projectMemberService)

    @Test
    fun `listProjectMembers should return all project members`() {
        val members = listOf(ProjectMember(id = 1, role = "admin"), ProjectMember(id = 2, role = "member"))
        every { projectMemberService.findAll() } returns members

        val result = projectMemberController.listProjectMembers()

        assertEquals(members, result.toList())
        verify(exactly = 1) { projectMemberService.findAll() }
    }

    @Test
    fun `getProjectMember should return member when found`() {
        val member = ProjectMember(id = 1, role = "admin")
        every { projectMemberService.findById(1L) } returns member

        val response = projectMemberController.getProjectMember(1L)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(member, response.body())
        verify(exactly = 1) { projectMemberService.findById(1L) }
    }

    @Test
    fun `getProjectMember should return 404 when not found`() {
        every { projectMemberService.findById(1L) } returns null

        val response = projectMemberController.getProjectMember(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        verify(exactly = 1) { projectMemberService.findById(1L) }
    }

    @Test
    fun `createProjectMember should return 201 created`() {
        val member = ProjectMember(role = "editor")
        val savedMember = member.copy(id = 1L)
        every { projectMemberService.save(any()) } returns savedMember

        val response = projectMemberController.createProjectMember(member)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals(savedMember, response.body())
        verify(exactly = 1) { projectMemberService.save(any()) }
    }

    @Test
    fun `deleteProjectMember should return 204 no content`() {
        every { projectMemberService.delete(1L) } returns Unit

        val response = projectMemberController.deleteProjectMember(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.status)
        verify(exactly = 1) { projectMemberService.delete(1L) }
    }
}
