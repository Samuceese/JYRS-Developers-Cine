package org.example.demo.productos.complementos.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.demo.productos.complementos.dto.ComplementoDto
import org.example.demo.productos.complementos.errors.ComplementoError
import org.example.demo.productos.complementos.mappers.toComplemento
import org.example.demo.productos.models.Complemento
import org.lighthousegames.logging.logging
import java.io.File
import java.io.InputStream

private val logger=logging()

/**
 * Manejamos adecuadamente la carga de datos del archivo csv.
 * @return Devuelve un objeto de tipo complemento si la operación esta bien, si no devuelve un error producido por un fallo al procesar el csv.
 * @author Raúl Fernández, Javier Hernández, Yahya El Hadri, Samuel Cortés
 * @since 1.0
 */

class ComplementoStorageImpl:ComplementoStorage {
    override fun load(file: File): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Carganado complementos desde fichero Csv" }
        return try {
            Ok(file.reader().readLines().drop(1)
                .map {
                    val data = it.split(",")
                    ComplementoDto(
                        tipoComplemento = data[0],
                        nombre = data[1],
                        precio = data[2],
                    ).toComplemento()
                }
            )
        }catch (e: Exception){
            logger.error { "Error al cargar el fichero csv " }
            Err((ComplementoError.FicheroNoValido("Error al leer el fichero csv")))
        }
    }

}