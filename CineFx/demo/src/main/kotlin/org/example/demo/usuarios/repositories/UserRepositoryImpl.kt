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
        return create(user)
    }

    override fun cambioContraseña(email: String, contraseña: String): Usuario? {
        logger.debug { "cambiando contraseña en email: $email" }
        findByEmail(email).let {
            it?.contraseña = contraseña
        }
        return findByEmail(email)
    }

    private fun newID(): String{
        logger.debug { "Generando nuevo id para cliente" }
        var id: String
        do {
            var letras = ""
            letras = List(3){('A'..'Z').random()}.joinToString { "" }
            val numeros = List(3){ Random.nextInt(0,10)}.joinToString { "" }
            id = letras + numeros
        }while (findById(id) != null)
        return id

    }

    private fun create(user: Usuario): Usuario{
        logger.debug { "create: $user" }
        db.transaction {
            db.insert(
                id = newID(),
                email = user.email,
                nombre = user.nombre,
                apellidos = user.apellidos,
                tipo = "cliente",
                contrasena = user.contraseña.encodeToBase64()
            )
        }
        return db.selectLastInserted().executeAsOne().toUsuario()
    }
    override fun findByEmail(email: String): Usuario? {
        logger.debug { "Buscando usuario por email $email" }
        return db.selectByEmail(email).executeAsOneOrNull()?.toUsuario()
    }

    override fun findById(id: String): Usuario? {
        logger.debug { "Buscando usuario por id: $id" }
        return db.selectById(id).executeAsOneOrNull()?.toUsuario()
    }
}