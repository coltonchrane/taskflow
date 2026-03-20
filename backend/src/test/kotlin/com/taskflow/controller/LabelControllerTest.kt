package com.taskflow.controller

import com.taskflow.models.Label
import com.taskflow.service.LabelService
import io.micronaut.http.HttpStatus
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlin.test.*

@MicronautTest
class LabelControllerTest {

    private val labelService: LabelService = mockk()
    private val labelController = LabelController(labelService)

    @Test
    fun `listLabels should return all labels`() {
        val labels = listOf(Label(id = 1, name = "Label 1"), Label(id = 2, name = "Label 2"))
        every { labelService.findAll() } returns labels

        val result = labelController.listLabels()

        assertEquals(labels, result.toList())
        verify(exactly = 1) { labelService.findAll() }
    }

    @Test
    fun `getLabel should return label when found`() {
        val label = Label(id = 1, name = "Label 1")
        every { labelService.findById(1L) } returns label

        val response = labelController.getLabel(1L)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(label, response.body())
        verify(exactly = 1) { labelService.findById(1L) }
    }

    @Test
    fun `getLabel should return 404 when not found`() {
        every { labelService.findById(1L) } returns null

        val response = labelController.getLabel(1L)

        assertEquals(HttpStatus.NOT_FOUND, response.status)
        verify(exactly = 1) { labelService.findById(1L) }
    }

    @Test
    fun `createLabel should return 201 created`() {
        val label = Label(name = "New Label")
        val savedLabel = label.copy(id = 1L)
        every { labelService.save(any()) } returns savedLabel

        val response = labelController.createLabel(label)

        assertEquals(HttpStatus.CREATED, response.status)
        assertEquals(savedLabel, response.body())
        verify(exactly = 1) { labelService.save(any()) }
    }

    @Test
    fun `deleteLabel should return 204 no content`() {
        every { labelService.delete(1L) } returns Unit

        val response = labelController.deleteLabel(1L)

        assertEquals(HttpStatus.NO_CONTENT, response.status)
        verify(exactly = 1) { labelService.delete(1L) }
    }
}
