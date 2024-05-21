package org.example.demo.usuarios.models

import org.example.demo.usuarios.repositories.UserRepositoryImpl
abstract class Usuario(
    val id: Long = -1,
    val nombre: String,
    val apellidos: String,
    val email: String,
    var contraseña: String
)