package org.example.demo.usuarios.models

import org.example.demo.usuarios.repositories.UserRepositoryImpl
import kotlin.random.Random

class Cliente(
    id: Long,
    nombre: String,
    apellidos: String,
    contraseña: String,
    email: String
) : Usuario(id, nombre, apellidos, email, contraseña) 
