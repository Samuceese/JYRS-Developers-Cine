package org.example.demo.usuarios.mappers

import database.UsuariosEntity
import org.example.demo.usuarios.models.Admin
import org.example.demo.usuarios.models.Cliente
import org.example.demo.usuarios.models.Usuario


/**
 * Mapea un UsuarioEntity en un estudiante.
 * @return admin, cliente
 * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
 * @since 1.0
 */


fun UsuariosEntity.toUsuario(): Usuario{
    return when(this.tipo){
        "admin" -> Admin(id = id.toLong(), nombre = nombre, apellidos = apellidos, email = email, contraseña = contrasena)
        "cliente" -> Cliente(id = id.toLong(), nombre = nombre, apellidos = apellidos, email = email, contraseña = contrasena)
        else -> throw IllegalArgumentException("Tipo de usuario no soportado")
    }
}