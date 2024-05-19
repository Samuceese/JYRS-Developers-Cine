package org.example.demo.usuarios.services

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.mapBoth
import org.example.demo.usuarios.cache.CacheUsuario
import org.example.demo.usuarios.errors.UserError
import org.example.demo.usuarios.models.Usuario
import org.example.demo.usuarios.repositories.UserRepository
import org.lighthousegames.logging.logging

private val logger = logging()
class UserServiceImpl(
    private val repository: UserRepository,
    private val cacheUsuario: CacheUsuario
): UserService {
    override fun save(user: Usuario): Result<Usuario, UserError> {
        logger.debug { "Guardando Usuario: $user" }
        repository.save(user).also {
            cacheUsuario.put(user.id, user)
            return Ok(it)
        }
    }

    override fun cambioContraseña(email: String, contraseña: String): Result<Usuario, UserError> {
        TODO("Not yet implemented")
    }

    override fun findByEmail(email: String): Result<Usuario, UserError> {
        logger.debug { "Buscando Usuario por email: $email" }
        return repository.findByEmail(email)?.let {
            Ok(it)
        } ?: Err(UserError.UserNotFound("No se ha encontrado usuario con email $email"))
    }

    override fun findById(id: String): Result<Usuario, UserError> {
        logger.debug { "Buscando Usuario por id: $id" }
        return cacheUsuario.get(id).mapBoth(
            success = {
                logger.debug { "Usuario encotnrado en la cache" }
                Ok(it)
            },
            failure = {
                logger.debug { "Butaca no encontrada en la cache" }
                repository.findById(id)?.also {
                    cacheUsuario.put(id, it)
                }?.let {
                    Ok(it)
                }?: Err(UserError.UserNotFound("Usuario no encontrado con  id: $id"))
            }
        )
    }
}