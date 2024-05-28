package org.example.demo.productos.complementos.dto

import org.example.demo.productos.dto.ProductoDto

/**
 * Clase Dto de complemento
 * @property tipoComplemento
 * @property nombre
 * @property precio
 * @author Raúl Fernández, Yahya El Hadri, Samuel Cortés, Javier Hernández
 * @since 1.0
 */

class ComplementoDto(
    val tipoComplemento: String,
    val nombre:String,
    val precio:String
): ProductoDto()