package org.example.demo.usuarios.dto

import kotlinx.serialization.Serializable

@Serializable
data class UsuarioDto(
    val id: String,
    val tipo:String,
    val nombre: String,
    val apellidos: String,
    val email: String,
    var contraseña: String
)