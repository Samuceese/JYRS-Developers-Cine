package org.example.demo.usuarios.models

abstract class Usuario(
    val id: String,
    val nombre: String,
    val apellidos: String,
    val email: String,
    var contraseña: String
) {
}