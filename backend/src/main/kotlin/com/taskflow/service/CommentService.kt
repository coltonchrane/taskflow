package com.taskflow.service

import com.taskflow.models.Comment
import com.taskflow.repository.CommentRepository
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class CommentService(private val commentRepository: CommentRepository) {

    fun findAll(): Iterable<Comment> = commentRepository.findAll()

    fun findById(id: Long): Comment? = commentRepository.findById(id).orElse(null)

    fun save(comment: Comment): Comment {
        if (comment.id == null) {
            comment.createdAt = LocalDateTime.now()
        }
        return commentRepository.save(comment)
    }

    fun delete(id: Long) {
        commentRepository.deleteById(id)
    }
}
