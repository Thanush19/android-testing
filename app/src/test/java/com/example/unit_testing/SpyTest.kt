import androidx.lifecycle.ViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.spy
import org.mockito.Mockito.verify


interface EmailSender {
    fun sendEmail(to: String, subject: String, body: String)
}


//    - Just stores the last sent email for inspection (instead of really sending it).
class RealEmailSender : EmailSender {
    var lastTo: String? = null
    var lastSubject: String? = null
    var lastBody: String? = null

    override fun sendEmail(to: String, subject: String, body: String) {
        lastTo = to
        lastSubject = subject
        lastBody = body
    }
}

// 3️⃣ Class under test
class NotificationViewModel(private val emailSender: EmailSender) : ViewModel() {

    fun sendWelcomeEmail(userEmail: String) {
        emailSender.sendEmail(
            to = userEmail,
            subject = "Welcome!",
            body = "Thanks for joining our app."
        )
    }
}

// 4️⃣ Test with a Spy
//    - A spy wraps the real object, so real methods are still called.
//    - We can verify both behavior (method calls) and state changes.
class NotificationViewModelTest {

    @Test
    fun `sendWelcomeEmail should send correct email`() {
        val realSender = RealEmailSender()
        val spySender = spy(realSender)
        val viewModel = NotificationViewModel(spySender)

        viewModel.sendWelcomeEmail("test@example.com")

        verify(spySender).sendEmail(
            "test@example.com",
            "Welcome!",
            "Thanks for joining our app."
        )

        // Assert: Check state from the real object
        assertEquals("test@example.com", realSender.lastTo)
        assertEquals("Welcome!", realSender.lastSubject)
        assertEquals("Thanks for joining our app.", realSender.lastBody)
    }
}
