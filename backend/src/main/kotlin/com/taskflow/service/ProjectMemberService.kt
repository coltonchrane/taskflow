package com.taskflow.service

import com.taskflow.models.ProjectMember
import com.taskflow.repository.ProjectMemberRepository
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class ProjectMemberService(private val projectMemberRepository: ProjectMemberRepository) {

    fun findAll(): Iterable<ProjectMember> = projectMemberRepository.findAll()

    fun findById(id: Long): ProjectMember? = projectMemberRepository.findById(id).orElse(null)

    fun save(projectMember: ProjectMember): ProjectMember {
        if (projectMember.id == null) {
            projectMember.createdAt = LocalDateTime.now()
        }
        return projectMemberRepository.save(projectMember)
    }

    fun delete(id: Long) {
        projectMemberRepository.deleteById(id)
    }
}
