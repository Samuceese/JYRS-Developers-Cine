package productos.complemento.storage

import org.example.demo.productos.butaca.storage.ButacaStorageImpl
import org.example.demo.productos.butaca.validator.ButacaValidator
import org.example.demo.productos.complementos.storage.ComplementoStorageImpl
import org.example.demo.productos.models.*
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

class TestStorageComplemento {

    private var storage: ComplementoStorageImpl = ComplementoStorageImpl()

    private var myFile: InputStream = Files.newInputStream(Path.of("C:\\Users\\anasm\\proyecto final\\JYRS-Developers-Cine\\CineFx\\demo\\src\\test\\resources\\data\\com-test.csv"))

    @Test
    fun loadCsv() {

        val butacas= listOf(Bebida("AGUA",CategoriaBebida.AGUA))

        val result = storage.load(myFile)

        assertTrue(result.isOk)
        assertEquals(butacas.toList().size,result.value.size)
    }

}