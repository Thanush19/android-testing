import androidx.lifecycle.ViewModel
import org.junit.Test
import org.mockito.Mockito.*

/**
 * Represents an external payment processing system.
 * In production, this might connect to a payment gateway API.
 * Here, we only define the contract (interface) so we can mock it in tests.
 */
interface PaymentProcessor {
    fun processPayment(amount: Double)
}

class CheckoutViewModel(private val paymentProcessor: PaymentProcessor) : ViewModel() {

    fun checkout(totalAmount: Double) {
        if (totalAmount > 0) {
            paymentProcessor.processPayment(totalAmount)
        }
    }
}

/**
 *
 * Why use a MOCK here?
 * - We want to verify that `CheckoutViewModel` calls `processPayment()` with the correct value.
 * - Using a real PaymentProcessor would trigger actual payment logic (not desirable in tests).
 * - A mock lets us:
 *      1. Intercept the method call.
 *      2. Check the exact arguments passed.
 *      3. Ensure no other unexpected calls are made.
 * - This makes the test purely about *interaction verification*, not about executing real payment code.
 */
class CheckoutViewModelTest {

    @Test
    fun `checkout should call payment processor with correct amount`() {
        val mockProcessor = mock(PaymentProcessor::class.java)

        val viewModel = CheckoutViewModel(mockProcessor)

        viewModel.checkout(99.99)

        verify(mockProcessor, times(1)).processPayment(99.99)

        verifyNoMoreInteractions(mockProcessor)
    }
}
