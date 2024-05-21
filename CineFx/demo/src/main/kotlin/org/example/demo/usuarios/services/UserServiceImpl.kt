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

/**
 * Servicio que maneja el repositorio de usuario y la cache.
 * @param UserRepository, CacheUsuario el repositorio y la cache de usuario.
 * @return Se devuelve el objeto usuario estando ya guardado.
 * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
 * @since 1.0
 */

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

    /**
     * Servicio donde nos encargamos de cambiar la contraseña de un usuario en el sistema.
     * @param email
     * @param contraseña
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun cambioContraseña(email: String, contraseña: String): Result<Usuario, UserError> {
        logger.debug { "Cambiando contraseña en email: $email" }
        return repository.cambioContraseña(email, contraseña)?.let {
            Ok(it)
        } ?: Err(UserError.UserNotFound("No se ha encontrado usuario con email: $email"))
    }

    /**
     * En este servicio buscamos un usuario en el repositorio por su dirección de correo electrónico.
     * @param email
     * @return Devuelve el usuario encontrado con su dirección de correo electrónico.
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun findByEmail(email: String): Result<Usuario, UserError> {
        logger.debug { "Buscando Usuario por email: $email" }
        return repository.findByEmail(email)?.let {
            Ok(it)
        } ?: Err(UserError.UserNotFound("No se ha encontrado usuario con email $email"))
    }

    /**
     * En este servicio buscamos un usuario en la cache por su ID.
     * @param id
     * @return Devuelve el usuario con su ID.
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun findById(id: Long): Result<Usuario, UserError> {
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