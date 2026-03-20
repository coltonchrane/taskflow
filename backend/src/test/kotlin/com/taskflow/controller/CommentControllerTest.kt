package com.taskflow.controller

import com.taskflow.models.Comment
import com.taskflow.service.CommentService
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.*

@MicronautTest
class CommentControllerTest {

    private val commentService: CommentService = mockk()
    private val commentController = CommentController(commentService)

    @Test
    fun `listComments should return all comments`() {
        val comments = listOf(Comment(id = 1, content = "Comment 1"), Comment(id = 2, content = "Comment 2"))
        every { commentService.findAll() } returns comments

        val result = commentController.listComments()

        assertEquals(comments, result.toList())
        verify(exactly = 1) { commentService.findAll() }
    }

    @Test
    fun `getComment should return comment when found`() {
        val comment = Comment(id = 1, content = "Comment 1")
        every { commentService.findById(1L) } returns comment

        val response = commentController.getComment(1L)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(comment, response.body())
        verify(exactly = 1) { commentService.findById(1L) }
    }

    @Test
    fun `getComment should return 404 when not found`() {
        every { commentService.findById(1L) } returns null

        val response = commentController.getComment(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        verify(exactly = 1) { commentService.findById(1L) }
    }

    @Test
    fun `createComment should return 201 created`() {
        val comment = Comment(content = "New Comment")
        val savedComment = comment.copy(id = 1L)
        every { commentService.save(any()) } returns savedComment

        val response = commentController.createComment(comment)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals(savedComment, response.body())
        verify(exactly = 1) { commentService.save(any()) }
    }

    @Test
    fun `deleteComment should return 204 no content`() {
        every { commentService.delete(1L) } returns Unit

        val response = commentController.deleteComment(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.status)
        verify(exactly = 1) { commentService.delete(1L) }
    }
}
