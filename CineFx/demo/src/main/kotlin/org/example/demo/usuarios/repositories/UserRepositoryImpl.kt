package org.example.demo.usuarios.repositories

import org.example.demo.database.SqlDelightManager
import org.example.demo.locale.encodeToBase64
import org.example.demo.usuarios.errors.UserError
import org.example.demo.usuarios.mappers.toUsuario
import org.example.demo.usuarios.models.Usuario
import org.lighthousegames.logging.logging
import kotlin.random.Random

private val logger = logging()
class UserRepositoryImpl: UserRepository {
    private val db = SqlDelightManager.databaseQueries
    override fun save(user: Usuario): Usuario {
        logger.debug { "save: $user" }
        db.transaction {
            db.insertUser(
                email = user.email,
                nombre = user.nombre,
                apellidos = user.apellidos,
                tipo = "cliente",
                contrasena = user.contraseña.encodeToBase64(),
            )
        }
        return user
    }

    override fun cambioContraseña(email: String, contraseña: String): Usuario? {
        logger.debug { "cambiando contraseña en email: $email" }
        findByEmail(email)?.let {
            it.contraseña = contraseña
        }
        return findByEmail(email)
    }

    override fun findByEmail(email: String): Usuario? {
        logger.debug { "Buscando usuario por email $email" }
        return db.selectByEmail(email).executeAsOneOrNull()?.toUsuario()
    }

    override fun findById(id: Long): Usuario? {
        logger.debug { "Buscando usuario por id: $id" }
        return db.selectById(id).executeAsOneOrNull()?.toUsuario()
    }
}