package com.taskflow.repository

import com.taskflow.models.Project
import com.taskflow.models.User
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@MicronautTest
class ProjectRepositoryTest {

    @Inject
    lateinit var projectRepository: ProjectRepository

    @Inject
    lateinit var userRepository: UserRepository

    private lateinit var testUser: User

    @BeforeEach
    fun setup() {
        projectRepository.deleteAll()
        userRepository.deleteAll()

        val uniqueUsername = "owner_${System.nanoTime()}"
        val uniqueEmail = "owner_${System.nanoTime()}@example.com"
        testUser = User(
            username = uniqueUsername, 
            email = uniqueEmail, 
            password = "password"
        )
        testUser = userRepository.save(testUser)
    }

    @Test
    fun `should save and find project`() {
        val project = Project(
            name = "Integration Project", 
            description = "Test description",
            owner = testUser
        )
        
        val savedProject = projectRepository.save(project)

        assertNotNull(savedProject.id)
        
        val foundProject = projectRepository.findById(savedProject.id!!).orElse(null)
        assertNotNull(foundProject)
        assertEquals("Integration Project", foundProject?.name)
        assertEquals(testUser.id, foundProject?.owner?.id)
    }

    @Test
    fun `should list projects`() {
        projectRepository.save(Project(name = "Project 1", owner = testUser))
        projectRepository.save(Project(name = "Project 2", owner = testUser))

        val projects = projectRepository.findAll()
        assertEquals(2, projects.size)
    }

    @Test
    fun `should delete project`() {
        val project = Project(name = "To Delete", owner = testUser)
        val savedProject = projectRepository.save(project)
        
        projectRepository.deleteById(savedProject.id!!)

        assertFalse(projectRepository.existsById(savedProject.id!!))
    }
}
