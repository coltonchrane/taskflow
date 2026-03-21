package com.taskflow.controller

import com.taskflow.models.User
import com.taskflow.service.UserService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/users")
class UserController(private val userService: UserService) {

    @Get("/")
    fun listUsers(): Iterable<User> = userService.findAll()

    @Get("/{id}")
    fun getUser(id: Long): HttpResponse<User> {
        val user = userService.findById(id)
        return if (user != null) {
            HttpResponse.ok(user)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post("/")
    fun createUser(@Body user: User): HttpResponse<User> {
        val savedUser = userService.save(user)
        return HttpResponse.created(savedUser)
    }

    @Put("/{id}")
    fun updateUser(id: Long, @Body user: User): HttpResponse<User> {
        val updatedUser = userService.update(id, user)
        return if (updatedUser != null) {
            HttpResponse.ok(updatedUser)
        } else {
            HttpResponse.notFound()
        }
    }

    @Delete("/{id}")
    fun deleteUser(id: Long): HttpResponse<Unit> {
        userService.delete(id)
        return HttpResponse.noContent()
    }
}
