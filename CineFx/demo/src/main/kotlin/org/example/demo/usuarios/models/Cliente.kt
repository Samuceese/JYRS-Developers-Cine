package org.example.demo.usuarios.models

/**
 * Representa a un cliente.
 * @property id
 * @property nombre
 * @property apellidos
 * @property contraseña
 * @property email
 * @author Javier Hernández, Yahya El Hadri, Samuel Cortés, Raúl Fernández
 * @since 1.0
 */

class Cliente(
    id: String,
    nombre: String,
    apellidos: String,
    contraseña: String,
    email: String
) : Usuario(id, nombre, apellidos, email, contraseña) {
}