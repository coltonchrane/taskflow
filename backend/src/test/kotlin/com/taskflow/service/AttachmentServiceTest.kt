package com.taskflow.service

import com.taskflow.models.Attachment
import com.taskflow.repository.AttachmentRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import kotlin.test.*

@MicronautTest
class AttachmentServiceTest {

    private val attachmentRepository: AttachmentRepository = mockk()
    private val attachmentService = AttachmentService(attachmentRepository)

    @Test
    fun `findAll should return all attachments`() {
        val attachments = listOf(Attachment(id = 1, filePath = "/path/1"), Attachment(id = 2, filePath = "/path/2"))
        every { attachmentRepository.findAll() } returns attachments

        val result = attachmentService.findAll()

        assertEquals(attachments, result)
        verify(exactly = 1) { attachmentRepository.findAll() }
    }

    @Test
    fun `findById should return attachment when exists`() {
        val attachment = Attachment(id = 1, filePath = "/path/1")
        every { attachmentRepository.findById(1L) } returns Optional.of(attachment)

        val result = attachmentService.findById(1L)

        assertNotNull(result)
        assertEquals(attachment, result)
        verify(exactly = 1) { attachmentRepository.findById(1L) }
    }

    @Test
    fun `save should set uploadedAt for new attachment`() {
        val attachment = Attachment(filePath = "New Path")
        val savedAttachment = attachment.copy(id = 1L)
        every { attachmentRepository.save(any()) } returns savedAttachment

        val result = attachmentService.save(attachment)

        assertNotNull(result.id)
        verify(exactly = 1) { attachmentRepository.save(any()) }
    }

    @Test
    fun `delete should call repository deleteById`() {
        every { attachmentRepository.deleteById(1L) } returns Unit

        attachmentService.delete(1L)

        verify(exactly = 1) { attachmentRepository.deleteById(1L) }
    }
}
