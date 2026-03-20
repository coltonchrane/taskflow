package com.taskflow.controller

import com.taskflow.models.Attachment
import com.taskflow.service.AttachmentService
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*

@Controller("/attachments")
class AttachmentController(private val attachmentService: AttachmentService) {

    @Get("/")
    fun listAttachments(): Iterable<Attachment> = attachmentService.findAll()

    @Get("/{id}")
    fun getAttachment(id: Long): HttpResponse<Attachment> {
        val attachment = attachmentService.findById(id)
        return if (attachment != null) {
            HttpResponse.ok(attachment)
        } else {
            HttpResponse.notFound()
        }
    }

    @Post("/")
    fun createAttachment(@Body attachment: Attachment): HttpResponse<Attachment> {
        val savedAttachment = attachmentService.save(attachment)
        return HttpResponse.created(savedAttachment)
    }

    @Delete("/{id}")
    fun deleteAttachment(id: Long): HttpResponse<Unit> {
        attachmentService.delete(id)
        return HttpResponse.noContent()
    }
}
