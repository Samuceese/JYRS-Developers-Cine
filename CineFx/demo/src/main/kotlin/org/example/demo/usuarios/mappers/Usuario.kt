package org.example.demo.usuarios.mappers

import database.UsuariosEntity
import org.example.demo.usuarios.models.Admin
import org.example.demo.usuarios.models.Cliente
import org.example.demo.usuarios.models.Usuario

fun UsuariosEntity.toUsuario(): Usuario{
    return when(this.tipo){
        "admin" -> Admin(id, nombre, apellidos, contrasena, email)
        "cliente" -> Cliente(id, nombre, apellidos, contrasena, email)
        else -> throw IllegalArgumentException("Tipo de usuario no soportado")
    }
}