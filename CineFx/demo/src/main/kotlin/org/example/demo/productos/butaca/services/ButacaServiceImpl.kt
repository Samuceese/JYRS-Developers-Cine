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
import kotlin.io.path.Path

private val logger=logging()

/**
 * Obtenemos todas las butacas del repositorio.
 * @param repository
 * @param validador
 * @param cache
 * @param storage
 * @return Si la operación de obteción de butacas se ha realizado correctamente se devuelve un ok, en caso de que no se devuelve un error.
 * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
 * @since 1.0
 */

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

    /**
     * Nos encargamos de obtener todas las butacas de un tipo determinado.
     * @param tipo
     * @return Si se realiza correctamente se devuelve un ok, si se encuentra algun fallo se devuelve error.
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun getByTipo(tipo: String): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por tipo: $tipo" }
        return Ok(repository.findByTipo(tipo))
    }

    /**
     * Nos encargamos de obtener todas las butacas por un ID en especifico.
     * @param id
     * @return Result indicando si la operación fue exitosa o no.
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

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

    /**
     * Nos encargamos de obtener todas las butacas por un estado en especifíco.
     * @param estado
     * @return Devuelve el resultado encapsulado en un objeto (result).
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun getByEstado(estado: String): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por estado: $estado" }
        return Ok(repository.findByEstado(estado))
    }

    /**
     * Nos encargamos de obtener todas las butacas por su ocupación especifíca.
     * @param ocupacion
     * @return Devuelve el resultado encapsulado en un objeto (result).
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun getByOcupacion(ocupacion: String): Result<List<Butaca>, ButacaError> {
        logger.debug { "Obteniendo butacas por ocupacion: $ocupacion" }
        return Ok(repository.findByOcupacion(ocupacion))
    }

    /**
     * Crear butaca.
     * @param butaca
     * @return Devuelve el resultado encapsulado en un objeto (result).
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun create(butaca: Butaca): Result<Butaca, ButacaError> {
        logger.debug { "Guardando butaca $butaca" }
        return validador.validarButaca(butaca).andThen {
            Ok(repository.save(it))
        }.andThen { p ->
            cache.put(p.id, p )
        }
    }

    /**
     * Actualizamos una butaca existente identificada por su ID con los datos proporcionados.
     * @param butaca
     * @return Devuelve el resultado encapsulado en un objeto (result).
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

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

    /**
     * Borramos la butaca que hayamos encontrado en el repositorio.
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */
     
    override fun deleteAll() {
        repository.deleteAll()
    }

    /**
     * Importamos datos de un archivo CSV para crear butacas en el sistema.
     * @param csvFile
     * @return Devuelve el resultado encapsulado en un objeto (result).
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun import(csvFile: File): Result<List<Butaca>, ButacaError> {
        logger.debug { "Cargando butacas desde CSV" }
        return storage.load(csvFile).andThen { butacas->
            butacas.forEach{ p->
                repository.save(p)
            }
            Ok(butacas)
        }
    }

    /**
     * Exportamos una lista de butacas a un archivo utilizando JSON.
     * @param fecha
     * @return Devuelve el resultado encapsulado en un objeto (result).
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun export(fecha:String,list: List<Butaca>): Result<Long, ButacaError> {
        logger.debug { "Guardando personajes en JSON" }
        return storage.save(Path("alalal").toFile(),list)
    }

}