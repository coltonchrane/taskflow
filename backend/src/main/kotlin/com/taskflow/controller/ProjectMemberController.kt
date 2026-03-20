package com.taskflow.controller

import com.taskflow.models.ProjectMember
import com.taskflow.service.ProjectMemberService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/project-members")
class ProjectMemberController(private val projectMemberService: ProjectMemberService) {

    @Get("/")
    fun listProjectMembers(): Iterable<ProjectMember> = projectMemberService.findAll()

    @Get("/{id}")
    fun getProjectMember(id: Long): HttpResponse<ProjectMember> {
        val projectMember = projectMemberService.findById(id)
        return if (projectMember != null) {
            HttpResponse.ok(projectMember)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post("/")
    fun createProjectMember(@Body projectMember: ProjectMember): HttpResponse<ProjectMember> {
        val savedProjectMember = projectMemberService.save(projectMember)
        return HttpResponse.created(savedProjectMember)
    }

    @Delete("/{id}")
    fun deleteProjectMember(id: Long): HttpResponse<Unit> {
        projectMemberService.delete(id)
        return HttpResponse.noContent()
    }
}
