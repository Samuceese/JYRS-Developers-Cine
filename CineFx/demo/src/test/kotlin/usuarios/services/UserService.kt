package usuarios.services

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import org.example.demo.cache.errors.CacheError
import org.example.demo.database.SqlDelightManager
import org.example.demo.usuarios.cache.CacheUsuario
import org.example.demo.usuarios.models.Admin
import org.example.demo.usuarios.models.Cliente
import org.example.demo.usuarios.repositories.UserRepository
import org.example.demo.usuarios.services.UserServiceImpl
import org.junit.jupiter.api.Assertions.*
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

    @Test
    fun getById(){
        val cliente = Cliente(id = -1L, "Manolo", "Escobar", "&2ht1XL$1t1X", "manolo123@gmail.es")
        whenever(cacheUsuario.get(-1L)).thenReturn(Ok(cliente))
        val result = serviceImpl.findById(-1L)
        assertAll(
            { assertTrue(result.isOk)},
            { assertEquals(result.value, cliente)}
        )
        verify(cacheUsuario, times(1)).get(-1L)
        verify(repository, times(0)).findById(-1L)
        verify(cacheUsuario, times(0)).put(-1L, cliente)
    }

    @Test
    fun getByEmail(){
        val cliente = Cliente(id = -1L, "Manolo", "Escobar", "&2ht1XL$1t1X", "manolo123@gmail.es")
        whenever(repository.findByEmail("manolo123@gmail.es")).thenReturn(cliente)
        val result = serviceImpl.findByEmail("manolo123@gmail.es")
        assertAll(
            { assertTrue(result.isOk) },
            { assertEquals(result.value, cliente)}
        )
        verify(repository, times(1)).findByEmail(cliente.email)
    }

    @Test
    fun getByEmailFail(){
        val cliente = Cliente(id = -1L, "Manolo", "Escobar", "&2ht1XL$1t1X", "manolo123@gmail.es")
        val message = "No se ha encontrado usuario con email ${cliente.email}"
        whenever(repository.findByEmail(cliente.email)).thenReturn(null)
        val result = serviceImpl.findByEmail("manolo123@gmail.es")
        assertAll(
            { assertTrue(result.isErr)},
            { assertEquals(result.value, null)}
        )
        verify(repository, times(1)).findByEmail(cliente.email)
    }

}
