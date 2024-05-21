package org.example.demo.usuarios.repositories

import org.example.demo.database.SqlDelightManager
import org.example.demo.locale.encodeToBase64
import org.example.demo.usuarios.errors.UserError
import org.example.demo.usuarios.mappers.toUsuario
import org.example.demo.usuarios.models.Usuario
import org.lighthousegames.logging.logging
import kotlin.random.Random


/**
 * Repositorio que se comunica con la base de datos de usuario.db
 * @author Raúl Fernández, Javier Hernández, Yahya El Hadri, Samuel Cortés
 * @property databaseClient de la base de datos usuario.db
 */

private val logger = logging()
class UserRepositoryImpl(

): UserRepository {

    private val db = SqlDelightManager.databaseQueries

    /**
     * Se encarga de guardar todos los datos que guardemos del usuario en la base de datos.
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

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

    /**
     * El cambio de contraseña del usuario.
     * @param email
     * @param contraseña
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun cambioContraseña(email: String, contraseña: String): Usuario? {
        logger.debug { "cambiando contraseña en email: $email" }
        findByEmail(email).let {
            it?.contraseña = contraseña
        }
        return findByEmail(email)
    }

    /**
     * Buscamos un usuario en la base de datos por su email y devuelve el usuario encontrado.
     * @return Devuelve un usuario una vez encontrado el usuario en la base de datos.
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun findByEmail(email: String): Usuario? {
        logger.debug { "Buscando usuario por email $email" }
        return db.selectByEmail(email).executeAsOneOrNull()?.toUsuario()
    }

    /**
     * Busca un usuario en la base de datos utilizando su ID unico y devuelve el usuario encontrado.
     * @return Devuelve el usuario encontrado.
     * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
     * @since 1.0
     */

    override fun findById(id: String): Usuario? {
        logger.debug { "Buscando usuario por id: $id" }
        return db.selectById(id).executeAsOneOrNull()?.toUsuario()
    }
}