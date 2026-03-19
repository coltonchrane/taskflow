package com.taskflow.controller

import com.taskflow.models.Task
import com.taskflow.service.TaskService
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.test.annotation.MockBean
import jakarta.inject.Inject
import spock.lang.Specification
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
        return Mock(TaskService)
    }

    void "test list tasks"() {
        given:
        def task = new Task(1L, 1L, null, "Test Task", "description", "pending", 0, null, LocalDateTime.now(), LocalDateTime.now())
        taskService.findAll() >> [task]

        when:
        def response = client.toBlocking().retrieve(HttpRequest.GET("/tasks"), Iterable)

        then:
        response.iterator().hasNext()
        response.iterator().next().title == "Test Task"
    }

    void "test create task"() {
        given:
        def task = new Task(null, 1L, null, "New Task", "desc", "pending", 0, null, LocalDateTime.now(), LocalDateTime.now())
        def savedTask = new Task(1L, 1L, null, "New Task", "desc", "pending", 0, null, LocalDateTime.now(), LocalDateTime.now())
        taskService.save(_) >> savedTask

        when:
        def response = client.toBlocking().exchange(HttpRequest.POST("/tasks", task), Task)

        then:
        response.status().code() == 201
        response.body().id == 1L
    }
}
