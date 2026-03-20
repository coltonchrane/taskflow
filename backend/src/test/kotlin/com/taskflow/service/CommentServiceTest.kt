package com.taskflow.service

import com.taskflow.models.Comment
import com.taskflow.repository.CommentRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import kotlin.test.*

@MicronautTest
class CommentServiceTest {

    private val commentRepository: CommentRepository = mockk()
    private val commentService = CommentService(commentRepository)

    @Test
    fun `findAll should return all comments`() {
        val comments = listOf(Comment(id = 1, content = "Comment 1"), Comment(id = 2, content = "Comment 2"))
        every { commentRepository.findAll() } returns comments

        val result = commentService.findAll()

        assertEquals(comments, result)
        verify(exactly = 1) { commentRepository.findAll() }
    }

    @Test
    fun `findById should return comment when exists`() {
        val comment = Comment(id = 1, content = "Comment 1")
        every { commentRepository.findById(1L) } returns Optional.of(comment)

        val result = commentService.findById(1L)

        assertNotNull(result)
        assertEquals(comment, result)
        verify(exactly = 1) { commentRepository.findById(1L) }
    }

    @Test
    fun `save should set createdAt for new comment`() {
        val comment = Comment(content = "New Comment")
        val savedComment = comment.copy(id = 1L)
        every { commentRepository.save(any()) } returns savedComment

        val result = commentService.save(comment)

        assertNotNull(result.id)
        verify(exactly = 1) { commentRepository.save(any()) }
    }

    @Test
    fun `delete should call repository deleteById`() {
        every { commentRepository.deleteById(1L) } returns Unit

        commentService.delete(1L)

        verify(exactly = 1) { commentRepository.deleteById(1L) }
    }
}
