package productos.complemento.storage

import org.example.demo.productos.complementos.storage.ComplementoStorageImpl
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File

class TestStorageComplemento {
    private  var storage = ComplementoStorageImpl()

    private lateinit var myFile: File

    @BeforeEach
    fun setUp(){
        myFile = File("data", "complemento.csv")
    }
    @Test
    fun loadCsv() {
        val result = storage.loadCsv(myFile)
        assertTrue(result.isOk)
    }

}