package org.example.demo.usuarios.models

class Admin(
    nombre: String,
    apellidos: String,
    contraseña: String,
    email: String
) : Usuario(nombre, apellidos, email, contraseña)  {
}