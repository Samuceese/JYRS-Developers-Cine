package usuarios.repository

import org.example.demo.database.SqlDelightManager
import org.example.demo.usuarios.repositories.UserRepository
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserRepositoryTest {

    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setup(){
        SqlDelightManager.initialize()
    }




}