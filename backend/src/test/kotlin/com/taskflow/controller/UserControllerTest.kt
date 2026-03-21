package com.taskflow.controller

import com.taskflow.models.User
import com.taskflow.service.UserService
import io.micronaut.http.HttpStatus
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.Test
import kotlin.test.assertEquals

class UserControllerTest {

    private val userService: UserService = mockk()
    private val userController = UserController(userService)

    @Test
    fun `listUsers should return all users`() {
        val users = listOf(User(id = 1, username = "user1"), User(id = 2, username = "user2"))
        every { userService.findAll() } returns users

        val result = userController.listUsers()

        assertEquals(users, result.toList())
        verify(exactly = 1) { userService.findAll() }
    }

    @Test
    fun `getUser should return user when found`() {
        val user = User(id = 1, username = "user1")
        every { userService.findById(1L) } returns user

        val response = userController.getUser(1L)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(user, response.body())
        verify(exactly = 1) { userService.findById(1L) }
    }

    @Test
    fun `getUser should return 404 when not found`() {
        every { userService.findById(1L) } returns null

        val response = userController.getUser(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        verify(exactly = 1) { userService.findById(1L) }
    }

    @Test
    fun `createUser should return 201 created`() {
        val user = User(username = "newuser")
        val savedUser = user.copy(id = 1L)
        every { userService.save(any()) } returns savedUser

        val response = userController.createUser(user)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals(savedUser, response.body())
        verify(exactly = 1) { userService.save(any()) }
    }

    @Test
    fun `updateUser should return updated user when found`() {
        val user = User(username = "updateduser")
        val updatedUser = user.copy(id = 1L)
        every { userService.update(1L, any()) } returns updatedUser

        val response = userController.updateUser(1L, user)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(updatedUser, response.body())
        verify(exactly = 1) { userService.update(1L, any()) }
    }

    @Test
    fun `updateUser should return 404 when not found`() {
        val user = User(username = "updateduser")
        every { userService.update(1L, any()) } returns null

        val response = userController.updateUser(1L, user)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        verify(exactly = 1) { userService.update(1L, any()) }
    }

    @Test
    fun `deleteUser should return 204 no content`() {
        every { userService.delete(1L) } returns Unit

        val response = userController.deleteUser(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.status)
        verify(exactly = 1) { userService.delete(1L) }
    }
}
