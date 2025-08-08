import org.junit.Assert.assertEquals
import org.junit.Test

// Repository interface (abstraction)
//    - Purpose: Defines user data operations.
//    - Benefit: Can replace real DB with a test double for fast, isolated tests.


class ViewModelForFakeImpl(private val repository: UserRepository) {
    fun registerUser(name: String) {
        repository.addUser(name)
    }

    fun getRegisteredUserCount(): Int {
        return repository.getUserCount()
    }
}
interface UserRepository{
    fun addUser(name:String)
    fun getUserCount():Int
}

class FakeUserRepository : UserRepository {
    private val users = mutableListOf<String>()
    override fun addUser(name: String) { users.add(name) }
    override fun getUserCount(): Int = users.size
}


class UserRepositoryTest {

    @Test
    fun `registerUser adds user and increases count`() {
        val repository = FakeUserRepository()
        val viewModel = ViewModelForFakeImpl(repository)
        viewModel.registerUser("Alice")

        assertEquals(1, viewModel.getRegisteredUserCount())
    }
}
