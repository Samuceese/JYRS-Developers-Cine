package productos.complemento.repositories

import org.example.demo.database.SqlDelightManager
import org.example.demo.productos.complementos.repositories.ComplementoRepositoryImpl
import org.example.demo.productos.models.Bebida
import org.example.demo.productos.models.CategoriaBebida
import org.example.demo.productos.models.CategoriaComida
import org.example.demo.productos.models.Comida
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestComplementoRepository {
    private lateinit var dbManager : SqlDelightManager
    private lateinit var complementoRepository : ComplementoRepositoryImpl

    @BeforeAll
    fun setUpAll() {
        dbManager = SqlDelightManager
        complementoRepository = ComplementoRepositoryImpl()
    }

    @AfterAll
    fun tearDown() {
        dbManager.clearData()
    }

    @Test
    fun findAll() {
        val complementos = complementoRepository.findAll()

        assertEquals(0, complementos.size)
    }

    @Test
    fun findById() {
        complementoRepository.save(Comida("FRUTOS SECOS",CategoriaComida.FRUTOSSECOS))
        val complemento = complementoRepository.findById("FRUTOS SECOS")

        assertEquals("FRUTOS SECOS", complemento?.id)
        assertEquals(CategoriaComida.FRUTOSSECOS, (complemento as Comida).nombre)
    }

    @Test
    fun findByIdNotFound() {
        val complemento = complementoRepository.findById("5")

        assertEquals(null, complemento)
    }

    @Test
    fun save() {
        val complemento = complementoRepository.save(
            Bebida(
                id = "AGUA",
                nombre = CategoriaBebida.AGUA,
            )
        )

        assertEquals("AGUA", complemento.id)
        assertEquals(CategoriaBebida.AGUA, (complemento as Bebida).nombre)
    }

    @Test
    fun update() {
        complementoRepository.save(Bebida("AGUA",CategoriaBebida.AGUA))
        val complemento = complementoRepository.update(
            "AGUA",
            Bebida(
                id = "AGUA",
                nombre = CategoriaBebida.AGUA,
            )
        )

        assertEquals("AGUA", complemento?.id)
        assertEquals(CategoriaBebida.AGUA, (complemento as Bebida).nombre)
    }

    @Test
    fun updateNotFound() {
        val complemento = complementoRepository.update(
            "15",
            Bebida(
                id = "REFRESCO",
                nombre = CategoriaBebida.REFRESCOS,
            )
        )

        assertEquals(null, complemento)
    }

    @Test
    fun delete() {
        complementoRepository.save(Bebida("AGUA",CategoriaBebida.AGUA))
        val complemento = complementoRepository.delete("AGUA")

        assertEquals("AGUA", complemento?.id)
    }

    @Test
    fun deleteNotFound() {
        val complemento = complementoRepository.delete("20")

        assertEquals(null, complemento)
    }
}