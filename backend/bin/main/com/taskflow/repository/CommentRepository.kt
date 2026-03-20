package com.taskflow.repository

import com.taskflow.models.Comment
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface CommentRepository : JpaRepository<Comment, Long>
