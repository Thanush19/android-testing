package com.example.unit_testing

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import android.widget.TextView
import android.widget.Button
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {
    private val viewModel: CounterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textViewCounter = findViewById<TextView>(R.id.textViewCounter)
        val buttonIncrement = findViewById<Button>(R.id.buttonIncrement)
        val buttonDecrement = findViewById<Button>(R.id.buttonDecrement)

        viewModel.counter.observe(this) { count ->
            textViewCounter.text = count.toString()
        }

        buttonIncrement.setOnClickListener {
            if (!viewModel.increment()) {
                Toast.makeText(this, "Counter cannot exceed 999", Toast.LENGTH_SHORT).show()
            }
        }

        buttonDecrement.setOnClickListener {
            if (viewModel.isPositive()) {
                viewModel.decrement()
            } else {
                Toast.makeText(this, "Counter cannot go below 0", Toast.LENGTH_SHORT).show()
            }
        }
    }
}