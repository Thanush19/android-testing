/**
 * Example showing the use of a STUB test double.
 *
 * A stub is used when:
 * - You don’t care about how the dependency was called (no interaction checks).
 * - You just need it to return a fixed, predictable value.
 * - The test focuses on *what the code does with the value*, not how it got it.
 */

import androidx.lifecycle.ViewModel
import androidx.fragment.app.Fragment
import org.junit.Assert.assertEquals
import org.junit.Test

interface WeatherService {
    fun getTemperature(city: String): Int
}

/**
 * STUB implementation of WeatherService for testing.
 *
 * Why a STUB here?
 * - Always returns the same temperature (25°C) for any city.
 * - Lets us test ViewModel logic without depending on a real API.
 * - No need for network calls or real data.
 */
class StubWeatherService : WeatherService {
    override fun getTemperature(city: String): Int = 25
}

/**
 * ViewModel that depends on WeatherService.
 * In tests, we’ll pass a StubWeatherService so results are always predictable.
 */
class WeatherViewModel(private val service: WeatherService) : ViewModel() {
    fun getWeatherMessage(city: String): String {
        val temp = service.getTemperature(city)
        return "It’s $temp°C in $city"
    }
}

/**
 * Fragment example using the stubbed ViewModel.
 *
 * Why stubs are useful for UI:
 * - Lets you preview and test UI with fixed data.
 * - Example: Always show “It’s 25°C in London” without real network calls.
 */
class WeatherFragment : Fragment() {
    private val viewModel = WeatherViewModel(StubWeatherService())
}

class WeatherViewModelTest {

    @Test
    fun `getWeatherMessage should use stubbed temperature`() {
        val stubService = StubWeatherService()
        val viewModel = WeatherViewModel(stubService)

        val message = viewModel.getWeatherMessage("ch")

        assertEquals("It’s 25°C in ch", message)
    }
}
