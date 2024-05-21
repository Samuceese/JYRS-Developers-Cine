package ventas.repositories

import org.example.demo.database.SqlDelightManager
import org.example.demo.productos.butaca.repositories.ButacaRepository
import org.example.demo.productos.butaca.repositories.ButacaRepositoryImpl
import org.example.demo.productos.complementos.repositories.ComplementoRepository
import org.example.demo.productos.complementos.repositories.ComplementoRepositoryImpl
import org.example.demo.productos.models.*
import org.example.demo.usuarios.models.Cliente
import org.example.demo.usuarios.repositories.UserRepository
import org.example.demo.usuarios.repositories.UserRepositoryImpl
import org.example.demo.venta.models.LineaVenta
import org.example.demo.venta.models.Venta
import org.example.demo.venta.repositories.VentasRepositoryImpl
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension::class)
class TestVentaRepository {
    private lateinit var dbManager: SqlDelightManager

    @InjectMocks
    private lateinit var ventaRepository: VentasRepositoryImpl

    @Mock
    private lateinit var butacaRepository: ButacaRepository

    @Mock
    private lateinit var complementoRepository: ComplementoRepository

    @Mock
    private lateinit var clienteRepository: UserRepository

    private lateinit var clienteMuestra: Cliente
    private lateinit var complementoMuestra: Complemento
    private lateinit var butacaMuestra: Butaca
    private lateinit var lineaVentaMuestra1: LineaVenta
    private lateinit var lineaVentaMuestra2: LineaVenta
    private lateinit var lineaVentaMuestra3: LineaVenta

    @BeforeAll
    fun setUpAll(){

        dbManager= SqlDelightManager
        butacaRepository= ButacaRepositoryImpl()
        complementoRepository= ComplementoRepositoryImpl()
        clienteRepository= UserRepositoryImpl()
        ventaRepository = VentasRepositoryImpl(clienteRepository, butacaRepository, complementoRepository)

        clienteMuestra = Cliente(
            id=1,
            nombre="User",
            apellidos = "user",
            email = "user@gmail.com",
            contraseña = "123456789",
        )
        complementoMuestra = Bebida(
            id="AGUA",
            nombre = CategoriaBebida.AGUA,
        )
        butacaMuestra = Butaca(
            id="A1",
            estado = Estado.ACTIVA,
            tipo = Tipo.NORMAL,
        )
        lineaVentaMuestra1 = LineaVenta(
            producto = butacaMuestra,
            cantidad = 1,
            precio = 5.0,
        )
        lineaVentaMuestra2 = LineaVenta(
            producto = complementoMuestra,
            cantidad = 1,
            precio = 3.0,
        )
        lineaVentaMuestra3 = LineaVenta(
            producto = complementoMuestra,
            cantidad = 2,
            precio = 3.0,
        )
    }

    @AfterAll
    fun tearDown(){
        dbManager.clearData()
    }

    @Test
    fun findAll(){
        ventaRepository.save(Venta(
            cliente = clienteMuestra, lineas = listOf(lineaVentaMuestra1,lineaVentaMuestra2,lineaVentaMuestra3)
        ))
        val ventas=ventaRepository.getAll()

        assertEquals(1,ventas.size)
    }

    @Test
    fun save() {
        val ventaSaved = ventaRepository.save(
            Venta(
                cliente=clienteMuestra,
                lineas = listOf(lineaVentaMuestra1,lineaVentaMuestra2),
            )
        )

        assertEquals(clienteMuestra,ventaSaved.cliente)
        assertEquals(lineaVentaMuestra1,ventaSaved.lineas[0])
        assertEquals(lineaVentaMuestra2,ventaSaved.lineas[1])
    }



    @Test
    fun delete() {
        val ventaDeleted = ventaRepository.delete(UUID.fromString("37c712fb-5531-4f33-a744-0fdb65cd9dcf"))

        assertEquals(UUID.fromString("37c712fb-5531-4f33-a744-0fdb65cd9dcf"), ventaDeleted?.id)
    }

    @Test
    fun deleteNotFound() {
        val ventaDeleted = ventaRepository.delete(UUID.fromString("666712fb-5531-4f33-a744-0fdb65cd9dcf"))

        assertEquals(null, ventaDeleted)
    }


}