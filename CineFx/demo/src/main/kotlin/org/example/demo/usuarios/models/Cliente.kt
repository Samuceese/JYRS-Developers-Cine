package org.example.demo.usuarios.models

class Cliente(
    id: Long,
    nombre: String,
    apellidos: String,
    contraseña: String,
    email: String
) : Usuario(id, nombre, apellidos, email, contraseña)