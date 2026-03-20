package com.taskflow.repository

import com.taskflow.models.Attachment
import io.micronaut.data.annotation.Repository
import io.micronaut.data.jpa.repository.JpaRepository

@Repository
interface AttachmentRepository : JpaRepository<Attachment, Long>
