package org.example.demo.venta.services

import com.github.michaelbull.result.*
import org.example.demo.productos.butaca.repositories.ButacaRepository
import org.example.demo.productos.complementos.repositories.ComplementoRepository
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Complemento
import org.example.demo.usuarios.models7.Usuario
import org.example.demo.usuarios.repositories.UserRepository
import org.example.demo.venta.errors.VentaError
import org.example.demo.venta.models.LineaVenta
import org.example.demo.venta.models.Venta
import org.example.demo.venta.repositories.VentasRepository
import org.example.demo.venta.storage.VentasStorage
import org.lighthousegames.logging.logging
import java.io.File
import java.util.*

private val logger = logging()
class VentasServiceImpl(
    private val ventasRepository: VentasRepository,
    private val complementoRepository: ComplementoRepository,
    private val butacasRepository: ButacaRepository,
    private val ventasSotrageHtml: VentasStorage
): VentasService{
    override fun getById(id: UUID): Result<Venta, VentaError> {
        logger.debug { "Obteniendo venta por id: $id" }
        return ventasRepository.getById(id)
            ?. let { Ok(it) }
            ?: Err(VentaError.VentaNoEncontrada("No se ha encontrado la venta con id: $id"))
    }

    override fun create(venta: Venta): Result<Venta, VentaError> {
        logger.debug { "Creando venta: $venta" }
        return validateCliente(venta.cliente).mapBoth(
            success = {
                validateLineas(venta.lineas).mapBoth(
                    success = {
                        Ok(ventasRepository.save(venta))
                    },
                    failure = {
                        Err(VentaError.VentaNoAlmacenada("no se a podido almacenar la venta: ${venta.id}"))
                    }
                )
            },
            failure = {
                Err(VentaError.VentaNoAlmacenada("no se a podido almacenar la venta: ${venta.id}"))
            }
        )

    }

   fun validateLineas(lineas: List<LineaVenta>): Result<List<LineaVenta>, VentaError> {
        logger.debug { "Validando lineas - Existen Productos: $lineas" }
        lineas.forEach {
            logger.debug { "Validando linea: $it" }
            when(it.producto){
                is Butaca-> {
                    butacasRepository.findById(it.producto.id)
                        ?: return Err(VentaError.VentaNoValida("Producto no encontrado con id: ${it.producto.id}"))
                }
                is Complemento->{
                    complementoRepository.findById(it.producto.id)
                        ?: return Err(VentaError.VentaNoEncontrada("Complemento no encontrado con id: ${it.producto.id}"))
                }
            }
            if (it.cantidad <= 0) {
                return Err(VentaError.VentaNoValida("La cantidad de productos debe ser mayor que 0"))
            }
        }
        return Ok(lineas)
    }

    //TODO ¡¡¡ revisar
    private fun validateCliente(cliente: Usuario): Result<Usuario, VentaError> {
        logger.debug { "Validando cliente: $cliente" }
       return validateCliente(cliente).mapBoth(
            failure = {
                Err(VentaError.VentaNoValida("El cliente no se ha podido validar"))
            },
            success = {
                Ok(it)
            }
        )
    }
    override fun delete(id: UUID): Result<Venta, VentaError> {
        logger.debug { "Eliminando venta : $id" }
        return ventasRepository.delete(id)
            ?.let { Ok(it) }
            ?: Err(VentaError.VentaNoEncontrada("No se a encontrado la venta $id"))
    }

    override fun getAll(): Result<List<Venta>, VentaError> {
        logger.debug { "Obteniendo todas las ventas" }
        return Ok( ventasRepository.getAll())
            ?: Err(VentaError.VentaNoEncontrada("No se han encontrado las ventas"))
    }

    override fun exportToHtml(venta: Venta, htmlFile: File,pelicula:String): Result<Unit, VentaError> {
        logger.debug { "Exportando venta a fichero html $htmlFile" }
        return ventasSotrageHtml.export(venta, htmlFile, pelicula)
    }
}