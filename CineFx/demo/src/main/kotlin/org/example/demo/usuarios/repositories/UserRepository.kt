package org.example.demo.usuarios.repositories

import org.example.demo.usuarios.models.Usuario

interface UserRepository {
    fun save(user: Usuario): Usuario
    fun cambioContraseña(email: String, contraseña: String): Usuario?
    fun findByEmail(email: String): Usuario?
    fun findById(id: Long): Usuario?
}