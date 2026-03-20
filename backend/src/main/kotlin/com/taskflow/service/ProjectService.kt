package com.taskflow.service

import com.taskflow.models.Project
import com.taskflow.repository.ProjectRepository
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class ProjectService(private val projectRepository: ProjectRepository) {

    fun findAll(): Iterable<Project> = projectRepository.findAll()

    fun findById(id: Long): Project? = projectRepository.findById(id).orElse(null)

    fun save(project: Project): Project {
        val toSave = if (project.id == null) {
            project.copy(createdAt = LocalDateTime.now(), updatedAt = LocalDateTime.now())
        } else {
            project.copy(updatedAt = LocalDateTime.now())
        }
        return projectRepository.save(toSave)
    }

    fun delete(id: Long) {
        projectRepository.deleteById(id)
    }
}
