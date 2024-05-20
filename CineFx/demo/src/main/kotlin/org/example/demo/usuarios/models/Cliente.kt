package org.example.demo.usuarios.models

class Cliente(
    nombre: String,
    apellidos: String,
    contraseña: String,
    email: String
) : Usuario(nombre, apellidos, email, contraseña) {
}