package productos.butaca.services

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import org.example.demo.cache.errors.CacheError
import org.example.demo.productos.butaca.cache.ButacasCacheImpl
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.butaca.repositories.ButacaRepository
import org.example.demo.productos.butaca.services.ButacaServiceImpl
import org.example.demo.productos.butaca.storage.ButacaStorageImpl
import org.example.demo.productos.butaca.validator.ButacaValidator
import org.example.demo.productos.complementos.repositories.ComplementoRepository
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Estado
import org.example.demo.productos.models.Tipo
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

@ExtendWith(MockitoExtension::class)
class TestButacaService {
    @Mock
    private lateinit var mockButacaRepository: ButacaRepository

    @Mock
    private lateinit var mockButacaCache: ButacasCacheImpl

    @Mock
    private lateinit var storage: ButacaStorageImpl

    @Mock
    private lateinit var mockButacaValidator: ButacaValidator


    @InjectMocks
    private lateinit var service: ButacaServiceImpl

    @Test
    fun getAllButacas() {
        Mockito.`when`(mockButacaRepository.findAll()).thenReturn(listOf())
        val results = service.getAll()
        assertTrue(results.isOk)
        verify(mockButacaRepository, times(1)).findAll()
    }


    @Test
    fun getButacaById() {
        val butaca = Butaca("A1", Estado.ACTIVA, Tipo.NORMAL)

        Mockito.`when`(mockButacaCache.get(butaca.id)).thenReturn(Ok(butaca))

        val result = service.getById(butaca.id)

        assertTrue(result.isOk)
        assertTrue(result.value == butaca)

        verify(mockButacaCache, times(1)).get(butaca.id)
        verify(mockButacaRepository, times(0)).findById(butaca.id)
        verify(mockButacaCache, times(0)).put(butaca.id, butaca)
    }

    @Test
    fun getButacaByIdErr() {
        val butaca = Butaca("A1", Estado.ACTIVA, Tipo.NORMAL)
        val message = "No existe el valor en la cache"

        Mockito.`when`(mockButacaCache.get(butaca.id)).thenReturn(Err(CacheError(message)))
        Mockito.`when`(mockButacaRepository.findById(butaca.id)).thenReturn(null)

        val result = service.getById(butaca.id)

        assertTrue(result.isErr)
        assertFalse(result.value == butaca)

        verify(mockButacaCache, times(1)).get(butaca.id)
        verify(mockButacaRepository, times(1)).findById(butaca.id)
        verify(mockButacaCache, times(0)).put(butaca.id, butaca)
    }

    @Test
    fun saveButaca() {
        val butaca = Butaca("A3", Estado.ACTIVA, Tipo.NORMAL)
        Mockito.`when`(mockButacaValidator.validarButaca(butaca)).thenReturn(Ok(butaca))
        Mockito.`when`(mockButacaRepository.save(butaca)).thenReturn(butaca)
        Mockito.`when`(mockButacaCache.put(butaca.id, butaca)).thenReturn(Ok(butaca))

        val result = service.create(butaca)

        assertTrue(result.isOk)
        assertTrue(result.value == butaca)

        verify(mockButacaValidator, times(1)).validarButaca(butaca)
        verify(mockButacaRepository, times(1)).save(butaca)
        verify(mockButacaCache, times(1)).put(butaca.id, butaca)
    }

    @Test
    fun updateButaca() {
        val butaca = Butaca("A1", Estado.ACTIVA, Tipo.NORMAL)
        Mockito.`when`(mockButacaValidator.validarButaca(butaca)).thenReturn(Ok(butaca))
        Mockito.`when`(mockButacaRepository.update(butaca.id, butaca, butaca.ocupacion, butaca.precio))
            .thenReturn(butaca)
        Mockito.`when`(mockButacaCache.put(butaca.id, butaca)).thenReturn(Ok(butaca))

        val result = service.update(butaca.id, butaca, butaca.ocupacion, butaca.precio)

        assertTrue(result.isOk)
        assertTrue(result.value == butaca)

        verify(mockButacaValidator, times(1)).validarButaca(butaca)
        verify(mockButacaRepository, times(1)).update(butaca.id, butaca, butaca.ocupacion, butaca.precio)
        verify(mockButacaCache, times(1)).put(butaca.id, butaca)
    }

    @Test
    fun updateButacaErr() {
        val butaca = Butaca("C6", Estado.ACTIVA, Tipo.NORMAL)
        Mockito.`when`(mockButacaValidator.validarButaca(butaca)).thenReturn(Ok(butaca))
        Mockito.`when`(mockButacaRepository.update(butaca.id, butaca, butaca.ocupacion, butaca.precio)).thenReturn(null)

        val result = service.update(butaca.id, butaca, butaca.ocupacion, butaca.precio)

        assertTrue(result.isErr)
        assertTrue(result.error is ButacaError.ButacaNoActualizadas)
        assertEquals(result.error.mensage, "No se ha podido actualizar la butaca: ${butaca.id}")

        verify(mockButacaValidator, times(1)).validarButaca(butaca)
        verify(mockButacaRepository, times(1)).update(butaca.id, butaca, butaca.ocupacion, butaca.precio)
        verify(mockButacaCache, times(0)).put(butaca.id, butaca)
    }

    @Test
    fun export(){
        val lista = listOf(Butaca("A1", Estado.ACTIVA, Tipo.NORMAL))
        var myFile: InputStream = Files.newInputStream(Path.of("C:\\Users\\anasm\\proyecto final\\JYRS-Developers-Cine\\CineFx\\demo\\src\\test\\resources\\data\\csv-test.csv"))

        Mockito.`when`(storage.load(myFile)).thenReturn(Ok(lista))

        val result = storage.load(myFile)

        assertTrue(result.isOk)
        assertEquals(result.value.size,lista.size)

        verify(storage, times(1)).load(myFile)
    }
}
