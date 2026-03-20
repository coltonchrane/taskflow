package com.taskflow.controller

import com.taskflow.models.Project
import com.taskflow.service.ProjectService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/projects")
class ProjectController(private val projectService: ProjectService) {

    @Get("/")
    fun listProjects(): Iterable<Project> = projectService.findAll()

    @Get("/{id}")
    fun getProject(id: Long): HttpResponse<Project> {
        val project = projectService.findById(id)
        return if (project != null) {
            HttpResponse.ok(project)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post("/")
    fun createProject(@Body project: Project): HttpResponse<Project> {
        val savedProject = projectService.save(project)
        return HttpResponse.created(savedProject)
    }

    @Delete("/{id}")
    fun deleteProject(id: Long): HttpResponse<Unit> {
        projectService.delete(id)
        return HttpResponse.noContent()
    }
}
