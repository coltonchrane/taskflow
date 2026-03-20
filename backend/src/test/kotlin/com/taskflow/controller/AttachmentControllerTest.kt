package com.taskflow.controller

import com.taskflow.models.Attachment
import com.taskflow.service.AttachmentService
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.*

@MicronautTest
class AttachmentControllerTest {

    private val attachmentService: AttachmentService = mockk()
    private val attachmentController = AttachmentController(attachmentService)

    @Test
    fun `listAttachments should return all attachments`() {
        val attachments = listOf(Attachment(id = 1, filePath = "/path/1"), Attachment(id = 2, filePath = "/path/2"))
        every { attachmentService.findAll() } returns attachments

        val result = attachmentController.listAttachments()

        assertEquals(attachments, result.toList())
        verify(exactly = 1) { attachmentService.findAll() }
    }

    @Test
    fun `getAttachment should return attachment when found`() {
        val attachment = Attachment(id = 1, filePath = "/path/1")
        every { attachmentService.findById(1L) } returns attachment

        val response = attachmentController.getAttachment(1L)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(attachment, response.body())
        verify(exactly = 1) { attachmentService.findById(1L) }
    }

    @Test
    fun `getAttachment should return 404 when not found`() {
        every { attachmentService.findById(1L) } returns null

        val response = attachmentController.getAttachment(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        verify(exactly = 1) { attachmentService.findById(1L) }
    }

    @Test
    fun `createAttachment should return 201 created`() {
        val attachment = Attachment(filePath = "/new/path")
        val savedAttachment = attachment.copy(id = 1L)
        every { attachmentService.save(any()) } returns savedAttachment

        val response = attachmentController.createAttachment(attachment)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals(savedAttachment, response.body())
        verify(exactly = 1) { attachmentService.save(any()) }
    }

    @Test
    fun `deleteAttachment should return 204 no content`() {
        every { attachmentService.delete(1L) } returns Unit

        val response = attachmentController.deleteAttachment(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.status)
        verify(exactly = 1) { attachmentService.delete(1L) }
    }
}
