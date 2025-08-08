
import org.junit.Test
import org.junit.Assert.assertEquals

//  A simple logger interface (abstraction)
//    - Purpose: Allows logging behavior to be replaced in tests (with a fake or mock logger)
interface Logger1 {
    fun log(msg: String)
}

class viewModelForDummyImpl(private val logger: Logger1) {
    fun doSomething(): String {
        val result = "Processed Data"

        logger.log("Data processed")

        return result
    }
}

class DummyLogger : Logger1 {
    override fun log(msg: String) { /* intentionally empty */ }
}


class DummyViewModelTest {

    @Test
    fun `doSomething returns expected result without side effects`() {
        val viewModel = viewModelForDummyImpl(DummyLogger())

        val result = viewModel.doSomething()

        assertEquals("Processed Data", result)

    }
}
