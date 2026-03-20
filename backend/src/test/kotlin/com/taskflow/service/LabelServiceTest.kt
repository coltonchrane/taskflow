package com.taskflow.service

import com.taskflow.models.Label
import com.taskflow.repository.LabelRepository
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import java.util.*
import kotlin.test.*

@MicronautTest
class LabelServiceTest {

    private val labelRepository: LabelRepository = mockk()
    private val labelService = LabelService(labelRepository)

    @Test
    fun `findAll should return all labels`() {
        val labels = listOf(Label(id = 1, name = "Label 1"), Label(id = 2, name = "Label 2"))
        every { labelRepository.findAll() } returns labels

        val result = labelService.findAll()

        assertEquals(labels, result)
        verify(exactly = 1) { labelRepository.findAll() }
    }

    @Test
    fun `findById should return label when exists`() {
        val label = Label(id = 1, name = "Label 1")
        every { labelRepository.findById(1L) } returns Optional.of(label)

        val result = labelService.findById(1L)

        assertNotNull(result)
        assertEquals(label, result)
        verify(exactly = 1) { labelRepository.findById(1L) }
    }

    @Test
    fun `save should set createdAt for new label`() {
        val label = Label(name = "New Label")
        val savedLabel = label.copy(id = 1L)
        every { labelRepository.save(any()) } returns savedLabel

        val result = labelService.save(label)

        assertNotNull(result.id)
        verify(exactly = 1) { labelRepository.save(any()) }
    }

    @Test
    fun `delete should call repository deleteById`() {
        every { labelRepository.deleteById(1L) } returns Unit

        labelService.delete(1L)

        verify(exactly = 1) { labelRepository.deleteById(1L) }
    }
}
