package productos.butaca.repositories


import org.example.demo.database.SqlDelightManager
import org.example.demo.productos.butaca.repositories.ButacaRepositoryImpl
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Estado
import org.example.demo.productos.models.Ocupacion
import org.example.demo.productos.models.Tipo
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TestButacaRepository {
    private lateinit var dbManager : SqlDelightManager
    private lateinit var butacaRepository : ButacaRepositoryImpl

    @BeforeAll
    fun setUpAll() {
        println("Iniciando tests...")
        dbManager = SqlDelightManager
        dbManager.initialize()
        butacaRepository = ButacaRepositoryImpl()
    }

    @AfterAll
    fun tearDown() {
        dbManager.initialize()
    }
    @Test
    fun findAll() {
        val butaca = butacaRepository.findAll()

        assertEquals(0, butaca.size)
    }

    @Test
    fun findById() {
         butacaRepository.save(
            Butaca(
                id = "A1",
                tipo = Tipo.NORMAL,
                estado = Estado.ACTIVA,
            )
        )
        val butaca = butacaRepository.findById("A1")

        assertEquals("A1", butaca?.id)
        assertEquals(Tipo.NORMAL, butaca?.tipo)
        assertEquals(Estado.ACTIVA, butaca?.estado)

    }

    @Test
    fun findByIdNotFound() {
        val butaca = butacaRepository.findById("5")

        assertEquals(null, butaca)
    }

    @Test
    fun save() {
        val butaca = butacaRepository.save(
            Butaca(
                id = "A1",
                tipo = Tipo.NORMAL,
                estado = Estado.ACTIVA,
            )
        )

        assertEquals("A1", butaca.id)
        assertEquals(Tipo.NORMAL, butaca.tipo)
        assertEquals(Estado.ACTIVA, butaca.estado)
    }

    @Test
    fun update() {
        val butaca = butacaRepository.update(
            "A1",
            Butaca(
                id = "A1",
                tipo = Tipo.NORMAL,
                estado = Estado.ACTIVA,
            ),Ocupacion.SELECCIONADA,7.0
        )

        assertEquals("A1", butaca?.id)
        assertEquals(Tipo.NORMAL, butaca?.tipo)
        assertEquals(Estado.ACTIVA, butaca?.estado)
    }

    @Test
    fun updateNotFound() {
        val butaca = butacaRepository.update(
            "15",
            Butaca(
                id = "A1",
                estado = Estado.ACTIVA,
                tipo = Tipo.VIP,
            ),Ocupacion.LIBRE,5.0
        )

        assertEquals(null, butaca)
    }


}