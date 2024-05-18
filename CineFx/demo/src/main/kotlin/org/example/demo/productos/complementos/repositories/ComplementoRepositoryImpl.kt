package org.example.demo.productos.complementos.repositories

import org.example.demo.database.SqlDelightManager
import org.example.demo.productos.complementos.mappers.toComplemento
import org.example.demo.productos.models.Bebida
import org.example.demo.productos.models.Comida
import org.example.demo.productos.models.Complemento
import org.lighthousegames.logging.logging

private val logger=logging()
class ComplementoRepositoryImpl:ComplementoRepository {
    private val db  = SqlDelightManager.databaseQueries
    override fun findAll(): List<Complemento> {
        logger.debug { "Obteniendo todas los complementos" }
        return db.getAllComplemetoEntity().executeAsList().map { it.toComplemento() }
    }

    override fun findById(id: String): Complemento? {
        logger.debug { "Obteniendo complemento por id: $id" }
        return db.getByIdComplemetoEntity(id)
            .executeAsOneOrNull()
            ?.toComplemento()
    }

    override fun findByTipo(tipo: String): List<Complemento> {
        logger.debug { "Obteniendo complementos por tipo: $tipo" }
        return db.getComplementoByTipo(tipo).executeAsList().map { it.toComplemento() }
    }

    override fun save(producto: Complemento): Complemento {
        logger.debug { "Guardando complemento: $producto" }

        when(producto) {
            is Bebida -> {
                db.transaction {
                    db.insertComplemento(
                        tipo = "BEBIDA",
                        id = producto.id,
                        nombre = producto.nombre.toString(),
                        precio = producto.precio.toLong()
                    )
                    println("llega aqui")
                }
            }

            is Comida -> {
                db.transaction {
                    db.insertComplemento(
                        tipo = "COMIDA",
                        id = producto.id,
                        nombre = producto.nombre.toString(),
                        precio = producto.precio.toLong()
                    )
                }
            }
        }
        return producto
    }

    override fun update(id: String, complemento: Complemento): Complemento? {
        logger.debug { "Actualizando complemento con id: $id" }
        val result = this.findById(id) ?: return null

        when(complemento){
            is Bebida ->{
                db.updateComplementoEntity(id,complemento.nombre.toString(),complemento.precio.toLong(),"BEBIDA")
            }
            is Comida ->{
                db.updateComplementoEntity(id,complemento.nombre.toString(),complemento.precio.toLong(),"COMIDA")
            }
        }

        return result
    }

    override fun delete(id: String): Complemento? {
        logger.debug { "Borrando complemento con id: $id" }
        val result = this.findById(id) ?: return null
        db.deleteComplementoByID(id)
        return result
    }
}