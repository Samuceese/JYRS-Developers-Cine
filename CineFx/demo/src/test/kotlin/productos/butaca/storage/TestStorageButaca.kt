package productos.butaca.storage

import org.example.demo.locale.toShortSpanishFormat
import org.example.demo.productos.butaca.storage.ButacaStorageImpl
import org.example.demo.productos.butaca.validator.ButacaValidator
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Estado
import org.example.demo.productos.models.Tipo
import org.example.demo.routes.RoutesManager
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.io.File
import java.nio.file.Files
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
class TestStorageButaca {
    @Mock
    private lateinit var mockButacaValidator: ButacaValidator

    @InjectMocks
    private lateinit var storage: ButacaStorageImpl
    private lateinit var myFile: File
    @BeforeEach
    fun setUp(){
        myFile = Files.createTempFile("butacas", ".csv").toFile()
    }
    @AfterEach
    fun tearDown(){
        Files.deleteIfExists(myFile.toPath())
    }

    @Test
    fun storageJson(){
        val butacas= listOf(Butaca("A1", Estado.ACTIVA, Tipo.NORMAL))
        val result = storage.save(myFile, butacas)
        assertTrue(result.isOk)
        assertEquals(result.value, butacas.size.toLong())
    }

    @Test
    fun loadCsv(){
        val result = storage.load(myFile)
        assertTrue(result.isOk)
    }


}