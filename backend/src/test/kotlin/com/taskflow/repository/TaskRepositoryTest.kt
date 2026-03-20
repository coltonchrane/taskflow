package com.taskflow.repository

import com.taskflow.models.Project
import com.taskflow.models.Task
import com.taskflow.models.User
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import jakarta.inject.Inject
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

@MicronautTest
class TaskRepositoryTest {

    @Inject
    lateinit var taskRepository: TaskRepository

    @Inject
    lateinit var projectRepository: ProjectRepository

    @Inject
    lateinit var userRepository: UserRepository

    private lateinit var testUser: User
    private lateinit var testProject: Project

    @BeforeEach
    fun setup() {
        // Clear existing data to be safe (though @MicronautTest should handle rollbacks)
        taskRepository.deleteAll()
        projectRepository.deleteAll()
        userRepository.deleteAll()

        // Create dependencies
        testUser = User(
            username = "testuser", 
            email = "test@example.com", 
            password = "securePassword123"
        )
        testUser = userRepository.save(testUser)

        testProject = Project(
            name = "Test Project", 
            description = "A project for testing tasks",
            owner = testUser
        )
        testProject = projectRepository.save(testProject)
    }

    @Test
    fun `should save and find task`() {
        val task = Task(
            title = "Integration Task", 
            description = "Detailed description",
            project = testProject,
            status = "in-progress",
            priority = 2,
            dueDate = LocalDateTime.now().plusDays(7)
        )
        
        val savedTask = taskRepository.save(task)

        assertNotNull(savedTask.id)
        
        val foundTask = taskRepository.findById(savedTask.id!!).orElse(null)
        assertNotNull(foundTask)
        assertEquals("Integration Task", foundTask?.title)
        assertEquals("in-progress", foundTask?.status)
        assertEquals(2, foundTask?.priority)
        assertEquals(testProject.id, foundTask?.project?.id)
    }

    @Test
    fun `should list multiple tasks for a project`() {
        val task1 = Task(title = "Task 1", project = testProject)
        val task2 = Task(title = "Task 2", project = testProject)
        
        taskRepository.saveAll(listOf(task1, task2))

        val tasks = taskRepository.findAll()
        assertEquals(2, tasks.size)
        assertTrue(tasks.any { it.title == "Task 1" })
        assertTrue(tasks.any { it.title == "Task 2" })
    }

    @Test
    fun `should update existing task`() {
        val task = Task(title = "Original Title", project = testProject)
        val savedTask = taskRepository.save(task)
        
        savedTask.title = "Updated Title"
        savedTask.status = "completed"
        taskRepository.update(savedTask)

        val updatedTask = taskRepository.findById(savedTask.id!!).get()
        assertEquals("Updated Title", updatedTask.title)
        assertEquals("completed", updatedTask.status)
    }

    @Test
    fun `should delete task`() {
        val task = Task(title = "Task to delete", project = testProject)
        val savedTask = taskRepository.save(task)
        
        taskRepository.deleteById(savedTask.id!!)

        assertFalse(taskRepository.existsById(savedTask.id!!))
    }
}
