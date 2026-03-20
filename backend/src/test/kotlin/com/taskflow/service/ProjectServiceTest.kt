package com.taskflow.service

import com.taskflow.models.Project
import com.taskflow.repository.ProjectRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import kotlin.test.*

@MicronautTest
class ProjectServiceTest {

    private val projectRepository: ProjectRepository = mockk()
    private val projectService = ProjectService(projectRepository)

    @Test
    fun `findAll should return all projects`() {
        val projects = listOf(Project(id = 1, name = "Project 1"), Project(id = 2, name = "Project 2"))
        every { projectRepository.findAll() } returns projects

        val result = projectService.findAll()

        assertEquals(projects, result)
        verify(exactly = 1) { projectRepository.findAll() }
    }

    @Test
    fun `findById should return project when exists`() {
        val project = Project(id = 1, name = "Project 1")
        every { projectRepository.findById(1L) } returns Optional.of(project)

        val result = projectService.findById(1L)

        assertNotNull(result)
        assertEquals(project, result)
        verify(exactly = 1) { projectRepository.findById(1L) }
    }

    @Test
    fun `findById should return null when not exists`() {
        every { projectRepository.findById(1L) } returns Optional.empty()

        val result = projectService.findById(1L)

        assertNull(result)
        verify(exactly = 1) { projectRepository.findById(1L) }
    }

    @Test
    fun `save should set createdAt and updatedAt for new project`() {
        val project = Project(name = "New Project")
        val savedProject = project.copy(id = 1L)
        every { projectRepository.save(any()) } returns savedProject

        val result = projectService.save(project)

        assertNotNull(result.id)
        verify(exactly = 1) { projectRepository.save(any()) }
    }

    @Test
    fun `delete should call repository deleteById`() {
        every { projectRepository.deleteById(1L) } returns Unit

        projectService.delete(1L)

        verify(exactly = 1) { projectRepository.deleteById(1L) }
    }
}
