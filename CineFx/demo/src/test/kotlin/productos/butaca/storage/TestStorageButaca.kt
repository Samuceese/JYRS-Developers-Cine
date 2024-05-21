package productos.butaca.storage

import org.example.demo.productos.butaca.storage.ButacaStorageImpl
import org.example.demo.productos.butaca.validator.ButacaValidator
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Estado
import org.example.demo.productos.models.Tipo
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

@ExtendWith(MockitoExtension::class)
class TestStorageButaca {
    @Mock
    private lateinit var mockButacaValidator: ButacaValidator

    @InjectMocks
    private lateinit var storage: ButacaStorageImpl

    private var myFile: InputStream = Files.newInputStream(Path.of("C:\\Users\\anasm\\proyecto final\\JYRS-Developers-Cine\\CineFx\\demo\\src\\test\\resources\\data\\csv-test.csv"))

    @Test
    fun loadCsv() {

        val butacas= listOf(Butaca("A1", Estado.ACTIVA, Tipo.NORMAL))

        val result = storage.load(myFile)

        assertTrue(result.isOk)
        assertEquals(butacas.toList().size,result.value.size)
    }

}