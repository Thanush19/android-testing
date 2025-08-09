package com.example.unit_testing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class CounterViewModel : ViewModel() {
    private val _counter = MutableLiveData<Int>(0)
    val counter: LiveData<Int> = _counter
    private val maxLimit = 999

    fun increment(): Boolean {
        val current = _counter.value ?: 0
        return if (current < maxLimit) {
            _counter.value = current + 1
            true
        } else {
            false
        }
    }

    fun decrement() {
        val current = _counter.value ?: 0
        if (current > 0) {
            _counter.value = current - 1
        }
    }

    fun isPositive(): Boolean {
        return (_counter.value ?: 0) > 0
    }
}