package org.example.demo.usuarios.storage
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.demo.usuarios.dto.UsuarioDto
import org.example.demo.usuarios.errors.UserError
import org.example.demo.usuarios.mappers.toUsuario
import org.example.demo.usuarios.mappers.toUsuarioDto
import org.example.demo.usuarios.models7.Usuario
import org.lighthousegames.logging.logging
import java.io.File

private val logger= logging()
class UsuarioStorageImpl:UsuarioStorage {
    override fun storeJson(file: File, lista: List<Usuario>): Result<Long, UserError> {
        logger.debug { "Guardando usuarios en fichero json $file" }
        return try {
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            val jsonString = json.encodeToString<List<UsuarioDto>>(lista.map { it.toUsuarioDto() })
            file.writeText(jsonString)
            Ok(lista.size.toLong())
        }catch(e:Exception){
            Err(UserError.StorageError("Error al escribir el JSON: ${e.message}"))
        }
    }

    override fun loadJson(file: File): Result<List<Usuario>, UserError> {
        logger.debug { "Leyendo Usuarios desde $file" }

        return try {
            val json = Json{
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            Ok(json.decodeFromString<List<UsuarioDto>>(file.readText()).map { it.toUsuario() })
        }catch (e: Exception) {
            Err(UserError.StorageError("Error al leer el JSON: ${e.message}"))
        }
    }
}