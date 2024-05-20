package org.example.demo.usuarios.models

import org.example.demo.usuarios.repositories.UserRepositoryImpl
import org.lighthousegames.logging.logging
import kotlin.random.Random

private val logger= logging()

abstract class Usuario(
    val nombre: String,
    val apellidos: String,
    val email: String,
    var contraseña: String
) {
    private val repo = UserRepositoryImpl()
    var id: String = ""
    init {
        this.id = newID()
    }
    fun newID(): String{
        logger.debug { "Generando nuevo id para cliente" }
        var id: String
        do {
            var letras = ""
            letras = List(3){('A'..'Z').random()}.joinToString { "" }
            val numeros = List(3){ Random.nextInt(0,10)}.joinToString { "" }
            id = letras + numeros
        }while (repo.findById(id) != null)
        return id

    }
}