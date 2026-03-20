package com.taskflow.controller

import com.taskflow.models.Comment
import com.taskflow.service.CommentService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/comments")
class CommentController(private val commentService: CommentService) {

    @Get("/")
    fun listComments(): Iterable<Comment> = commentService.findAll()

    @Get("/{id}")
    fun getComment(id: Long): HttpResponse<Comment> {
        val comment = commentService.findById(id)
        return if (comment != null) {
            HttpResponse.ok(comment)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post("/")
    fun createComment(@Body comment: Comment): HttpResponse<Comment> {
        val savedComment = commentService.save(comment)
        return HttpResponse.created(savedComment)
    }

    @Delete("/{id}")
    fun deleteComment(id: Long): HttpResponse<Unit> {
        commentService.delete(id)
        return HttpResponse.noContent()
    }
}
