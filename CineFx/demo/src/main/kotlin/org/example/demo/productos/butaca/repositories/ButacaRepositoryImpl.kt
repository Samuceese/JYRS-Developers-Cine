package org.example.demo.productos.butaca.repositories

import org.example.demo.database.SqlDelightManager
import org.example.demo.productos.butaca.mappers.toButaca
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Ocupacion
import org.lighthousegames.logging.logging

private val logger=logging()
class ButacaRepositoryImpl:ButacaRepository {
    private val db  = SqlDelightManager.databaseQueries

    override fun findAll(): List<Butaca> {
        logger.debug { "Obteniendo todas las butacas" }
        return db.getAllButacaEntity().executeAsList().map { it.toButaca() }
    }

    override fun save(producto: Butaca): Butaca {
        logger.debug { "Guardando butaca: $producto" }

        db.transaction {
            db.insertarbutaca(
                id = producto.id,
                estado = producto.estado.toString(),
                precio = producto.precio.toLong(),
                tipo = producto.tipo.toString(),
                ocupacion = producto.ocupacion.toString(),
                createAt = producto.create.toString()
            )
        }
        return producto
    }

    override fun findById(id: String): Butaca? {
        logger.debug { "Obteniendo butaca por id: $id" }
        return db.getByIdButacaEntity(id)
            .executeAsOneOrNull()
            ?.toButaca()
    }

    override fun findByTipo(tipo: String): List<Butaca> {
        logger.debug { "Obteniendo butacas por tipo: $tipo" }
        return db.getbutacaByTipo(tipo)
            .executeAsList()
            .map { it.toButaca() }
    }

    override fun update(id: String, butaca: Butaca,ocupacion: Ocupacion,precio:Double): Butaca? {
        logger.debug { "Actualizando butaca con id: $id" }
        val result = this.findById(id) ?: return null

        db.updateButacaEntity(
            id = id,
            estado = butaca.estado.toString(),
            tipo = butaca.tipo.toString(),
            ocupacion = ocupacion.toString(),
            precio = precio.toLong()
        )
        return result
    }

    override fun findByEstado(estado: String): List<Butaca> {
        logger.debug { "Obteniendo butacas por estado: $estado" }
        return db.getButacaByEstado(estado)
            .executeAsList()
            .map { it.toButaca() }
    }

    override fun findByOcupacion(ocupacion: String): List<Butaca> {
        logger.debug { "Obteniendo butacas por estado: $ocupacion" }
        return db.getButacaByOcupacion(ocupacion)
            .executeAsList()
            .map { it.toButaca() }
    }

    override fun deleteAll() {
        db.deleteAllButacaEntity()
    }
}