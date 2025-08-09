package com.example.unit_testing


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class CounterViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: CounterViewModel

    @Before
    fun setUp() {
        viewModel = CounterViewModel()
    }

    @Test
    fun `increment increases counter by 1`() {
        viewModel.increment()
        assertEquals(1, viewModel.counter.value)
    }

    @Test
    fun `decrement does not go below 0`() {
        viewModel.decrement()
        assertEquals(0, viewModel.counter.value)
    }

    @Test
    fun `isPositive returns false when counter is 0`() {
        assertEquals(false, viewModel.isPositive())
    }

    @Test
    fun `isPositive returns true when counter is greater than 0`() {
        viewModel.increment()
        assertEquals(true, viewModel.isPositive())
    }
}