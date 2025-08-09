package com.example.unit_testing

import android.widget.Button
import android.widget.TextView
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.shadows.ShadowToast
import org.robolectric.annotation.Config
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34], application = TestApplication::class)
class MainActivityTest {
    private lateinit var activity: MainActivity

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(MainActivity::class.java)
            .create()
            .start()
            .resume()
            .get()
    }

    @Test
    fun `initial counter value is 0`() {
        val textView = activity.findViewById<TextView>(R.id.textViewCounter)
        assertEquals("0", textView.text.toString())
    }

    @Test
    fun `clicking increment button increases counter`() {
        val textView = activity.findViewById<TextView>(R.id.textViewCounter)
        activity.findViewById<Button>(R.id.buttonIncrement).performClick()
        assertEquals("1", textView.text.toString())
    }

    @Test
    fun `clicking decrement button when counter is 0 shows toast`() {
        activity.findViewById<Button>(R.id.buttonDecrement).performClick()
        val toast = ShadowToast.getTextOfLatestToast()
        assertEquals("Counter cannot go below 0", toast)
    }

    @Test
    fun `clicking decrement button when counter is positive decreases counter`() {
        activity.findViewById<Button>(R.id.buttonIncrement).performClick()
        val textView = activity.findViewById<TextView>(R.id.textViewCounter)
        activity.findViewById<Button>(R.id.buttonDecrement).performClick()
        assertEquals("0", textView.text.toString())
    }

    @Test
    fun `clicking increment button at max limit shows toast and does not increase counter`() {
        val buttonIncrement = activity.findViewById<Button>(R.id.buttonIncrement)
        repeat(999) { buttonIncrement.performClick() }
        val textView = activity.findViewById<TextView>(R.id.textViewCounter)
        assertEquals("999", textView.text.toString())

        buttonIncrement.performClick()
        val toast = ShadowToast.getTextOfLatestToast()
        assertEquals("Counter cannot exceed 999", toast)
        assertEquals("999", textView.text.toString())
    }
}