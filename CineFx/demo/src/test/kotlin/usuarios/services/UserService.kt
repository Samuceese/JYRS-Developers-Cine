package usuarios.services

import org.example.demo.database.SqlDelightManager
import org.example.demo.usuarios.cache.CacheUsuario
import org.example.demo.usuarios.models.Admin
import org.example.demo.usuarios.repositories.UserRepository
import org.example.demo.usuarios.services.UserServiceImpl
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@ExtendWith(MockitoExtension::class)
class UserService {
    @Mock
    lateinit var repository: UserRepository
    @Mock
    lateinit var cacheUsuario: CacheUsuario
    @InjectMocks
    lateinit var serviceImpl: UserServiceImpl

    @BeforeEach
    fun setUp(){
        SqlDelightManager.initialize()
    }

    @Test
    fun findById(){
        val admin = Admin(1, "JYRS", "dev", "JyRs*05020303", "SomosJYRS@gmail.com" )
        whenever(repository.findById(1)).thenReturn(admin)
        val result = serviceImpl.findById(1)
        assertTrue(result.isErr)
        assertTrue(result.value == admin)

        verify(cacheUsuario, times(1)).get(1)
        verify(repository, times(1)).findById(1)
        verify(cacheUsuario, times(1)).put(1, admin)
    }

}