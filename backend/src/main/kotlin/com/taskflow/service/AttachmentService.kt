package com.taskflow.service

import com.taskflow.models.Attachment
import com.taskflow.repository.AttachmentRepository
import jakarta.inject.Singleton
import java.time.LocalDateTime

@Singleton
open class AttachmentService(private val attachmentRepository: AttachmentRepository) {

    fun findAll(): Iterable<Attachment> = attachmentRepository.findAll()

    fun findById(id: Long): Attachment? = attachmentRepository.findById(id).orElse(null)

    fun save(attachment: Attachment): Attachment {
        if (attachment.id == null) {
            attachment.uploadedAt = LocalDateTime.now()
        }
        return attachmentRepository.save(attachment)
    }

    fun delete(id: Long) {
        attachmentRepository.deleteById(id)
    }
}
