package com.taskflow.controller

import com.taskflow.models.Project
import com.taskflow.service.ProjectService
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.*

@MicronautTest
class ProjectControllerTest {

    private val projectService: ProjectService = mockk()
    private val projectController = ProjectController(projectService)

    @Test
    fun `listProjects should return all projects`() {
        val projects = listOf(Project(id = 1, name = "Project 1"), Project(id = 2, name = "Project 2"))
        every { projectService.findAll() } returns projects

        val result = projectController.listProjects()

        assertEquals(projects, result.toList())
        verify(exactly = 1) { projectService.findAll() }
    }

    @Test
    fun `getProject should return project when found`() {
        val project = Project(id = 1, name = "Project 1")
        every { projectService.findById(1L) } returns project

        val response = projectController.getProject(1L)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(project, response.body())
        verify(exactly = 1) { projectService.findById(1L) }
    }

    @Test
    fun `getProject should return 404 when not found`() {
        every { projectService.findById(1L) } returns null

        val response = projectController.getProject(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        verify(exactly = 1) { projectService.findById(1L) }
    }

    @Test
    fun `createProject should return 201 created`() {
        val project = Project(name = "New Project")
        val savedProject = project.copy(id = 1L)
        every { projectService.save(any()) } returns savedProject

        val response = projectController.createProject(project)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals(savedProject, response.body())
        verify(exactly = 1) { projectService.save(any()) }
    }

    @Test
    fun `deleteProject should return 204 no content`() {
        every { projectService.delete(1L) } returns Unit

        val response = projectController.deleteProject(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.status)
        verify(exactly = 1) { projectService.delete(1L) }
    }
}
