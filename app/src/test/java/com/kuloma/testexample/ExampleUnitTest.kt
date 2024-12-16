package com.kuloma.testexample

import com.kuloma.testexample.presentation.info.InfoViewModel
import org.junit.Test

import org.junit.Assert.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class ExampleUnitTest {
    private var viewModel: InfoViewModel = InfoViewModel()

    @Test
    fun testFormatTimestamp_ValidTimestamp() {
        val unixTimestamp = 1633072800L
        val result = viewModel.formatTimestamp(unixTimestamp)
        val expected = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date(unixTimestamp * 1000))
        assertEquals(expected, result)
    }

    @Test
    fun testFormatTimestamp_InvalidTimestamp() {
        val invalidTimestamp = -1L
        val result = viewModel.formatTimestamp(invalidTimestamp)
        assertEquals("Неверное время", result)
    }
}