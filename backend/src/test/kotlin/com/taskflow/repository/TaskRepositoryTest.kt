package com.taskflow.repository

import com.taskflow.models.Project
import com.taskflow.models.Task
import com.taskflow.models.User
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class TaskRepositoryTest : BaseRepositoryTest() {

    private lateinit var testUser: User
    private lateinit var testProject: Project

    @BeforeEach
    fun setup() {
        // Create dependencies
        val uniqueEmail = "test_${System.nanoTime()}@example.com"
        testUser = User(
            username = "testuser_${System.nanoTime()}", 
            email = uniqueEmail, 
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
    fun `should find tasks by projectId`() {
        val task1 = Task(title = "Project 1 Task 1", project = testProject)
        val task2 = Task(title = "Project 1 Task 2", project = testProject)
        
        // Create another project
        val otherProject = Project(
            name = "Other Project", 
            owner = testUser
        )
        val savedOtherProject = projectRepository.save(otherProject)
        val otherTask = Task(title = "Project 2 Task", project = savedOtherProject)
        
        taskRepository.saveAll(listOf(task1, task2, otherTask))

        val project1Tasks = taskRepository.findByProjectId(testProject.id!!).toList()
        assertEquals(2, project1Tasks.size)
        assertTrue(project1Tasks.all { it.project?.id == testProject.id })
        
        val project2Tasks = taskRepository.findByProjectId(savedOtherProject.id!!).toList()
        assertEquals(1, project2Tasks.size)
        assertEquals("Project 2 Task", project2Tasks[0].title)
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
