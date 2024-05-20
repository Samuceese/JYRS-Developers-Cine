package org.example.demo.usuarios.models

import org.example.demo.usuarios.repositories.UserRepositoryImpl
import kotlin.random.Random

abstract class Usuario(
    var id: String,
    val nombre: String,
    val apellidos: String,
    val email: String,
    var contraseña: String
)