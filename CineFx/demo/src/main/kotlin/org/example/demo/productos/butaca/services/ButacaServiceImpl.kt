package org.example.demo.productos.butaca.services

import com.github.michaelbull.result.*
import org.example.demo.productos.butaca.cache.ButacasCacheImpl
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.butaca.repositories.ButacaRepository
import org.example.demo.productos.butaca.storage.ButacaStorage
import org.example.demo.productos.butaca.validator.ButacaValidator
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Ocupacion
import org.lighthousegames.logging.logging
import java.io.File
import java.io.InputStream

private val logger=logging()
class ButacaServiceImpl(
    private val repository: ButacaRepository,
    private val validador: ButacaValidator,
    private val cache: ButacasCacheImpl,
    private val storage: ButacaStorage
) :ButacaService{
    override fun getAll(): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo todas las butacas" }
        return Ok(repository.findAll())
    }

    override fun getByTipo(tipo: String): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por tipo: $tipo" }
        return Ok(repository.findByTipo(tipo))
    }

    override fun getById(id: String): Result<Butaca, ButacaError> {
        return cache.get(id).mapBoth(
            success = {
                logger.debug { "Butaca encontrada en cache" }
                Ok(it)
            },
            failure = {
                logger.debug { "Butaca no encontrada en cache" }
                repository.findById(id)
                    ?.let { Ok(it) }
                    ?: Err(ButacaError.ButacaNoEncontrada("Butaca no encontrada con id: $id"))
            }
        )
    }

    override fun getByEstado(estado: String): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por estado: $estado" }
        return Ok(repository.findByEstado(estado))
    }

    override fun getByOcupacion(ocupacion: String): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por ocupacion: $ocupacion" }
        return Ok(repository.findByOcupacion(ocupacion))
    }

    override fun create(butaca: Butaca): Result<Butaca, ButacaError> {
        logger.debug { "Guardando butaca $butaca" }
        return validador.validarButaca(butaca).andThen {
            Ok(repository.save(it))
        }.andThen { p ->
            cache.put(p.id, p )
        }
    }

    override fun update(id: String, butaca: Butaca, ocupacion: Ocupacion, precio:Double): Result<Butaca, ButacaError> {
        logger.debug { "Actualizando butaca con id: $id" }
        return validador.validarButaca(butaca).andThen {  b ->
            repository.update(b.id, b,ocupacion,precio)
                ?.let { Ok(it) }
                ?: Err(ButacaError.ButacaNoActualizadas("No se ha podido actualizar la butaca: $id"))
        }.andThen {
            cache.put(id, butaca)
        }
    }

    override fun deleteAll() {
        repository.deleteAll()
    }

    override fun import(csvFile: InputStream): Result<List<Butaca>, ButacaError> {
        logger.debug { "Cargando butacas desde CSV" }
        return storage.load(csvFile).andThen { butacas->
            butacas.forEach{ p->
                repository.save(p)
            }
            Ok(butacas)
        }
    }

    override fun export(fecha:String,list: List<Butaca>): Result<Unit, ButacaError> {
        logger.debug { "Guardando personajes en JSON" }
        return storage.save(fecha,list)
    }

}