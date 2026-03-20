package com.taskflow.controller

import com.taskflow.models.Task
import com.taskflow.models.Project
import com.taskflow.service.TaskService
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject
import spock.lang.Specification
import spock.mock.DetachedMockFactory
import java.time.LocalDateTime

@MicronautTest
class TaskControllerSpec extends Specification {

    @Inject
    @Client("/")
    HttpClient client

    @Inject
    TaskService taskService

    @MockBean(TaskService)
    TaskService taskService() {
        return new DetachedMockFactory().Mock(TaskService)
    }

    void "test list tasks"() {
        given:
        def project = new Project(id: 1L, name: "Project")
        def task = new Task(id: 1L, project: project, title: "Test Task", description: "description", status: "pending", priority: 0, createdAt: LocalDateTime.now(), updatedAt: LocalDateTime.now())
        taskService.findAll() >> [task]

        when:
        def response = client.toBlocking().retrieve(HttpRequest.GET("/tasks"), List)

        then:
        !response.isEmpty()
        response[0].title == "Test Task"
    }

    void "test create task"() {
        given:
        def project = new Project(id: 1L, name: "Project")
        def task = new Task(id: null, project: project, title: "New Task", description: "desc", status: "pending", priority: 0, createdAt: LocalDateTime.now(), updatedAt: LocalDateTime.now())
        def savedTask = new Task(id: 1L, project: project, title: "New Task", description: "desc", status: "pending", priority: 0, createdAt: LocalDateTime.now(), updatedAt: LocalDateTime.now())
        taskService.save(_) >> savedTask

        when:
        def response = client.toBlocking().exchange(HttpRequest.POST("/tasks", task), Task)

        then:
        response.status().code() == 201
        response.body().id == 1L
    }
}
