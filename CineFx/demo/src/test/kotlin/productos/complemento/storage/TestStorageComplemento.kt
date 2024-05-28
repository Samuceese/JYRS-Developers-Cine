package productos.complemento.storage

import org.example.demo.productos.complementos.storage.ComplementoStorageImpl
import org.example.demo.productos.models.*
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
import java.io.InputStream
import java.nio.file.Files
import java.nio.file.Path

class TestStorageComplemento {
    private  var storage = ComplementoStorageImpl()

    private lateinit var myFile: File

    @BeforeEach
    fun setUp(){
        myFile = File("data", "complemento.csv")
    }
    @Test
    fun loadCsv() {
        val result = storage.load(myFile)
        assertTrue(result.isOk)
    }

}