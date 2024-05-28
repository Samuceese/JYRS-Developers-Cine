package org.example.demo.venta.dto

import org.example.demo.productos.dto.ProductoDto
import org.example.demo.usuarios.dto.UsuarioDto

data class LineaVentaDto(
    val id: String,
    val producto: ProductoDto,
    val cantidad: String,
    val precio: String,
    val createdAt: String,
    val updatedAt: String
)


data class VentaDto(
    val id: String,
    val cliente: UsuarioDto,
    val lineas: List<LineaVentaDto>,
    val total:String,
    val createdAt: String,
    val updatedAt: String
)