package org.example.demo.venta.models

import kotlinx.serialization.Serializable
import org.example.demo.LocalDateTimeSerializer
import org.example.demo.UUIDSerializer
import org.example.demo.productos.models.Producto
import java.time.LocalDateTime
import java.util.*
@Serializable
data class LineaVenta(
    @Serializable(with = UUIDSerializer::class) val id: UUID = UUID.randomUUID(),
    val producto: Producto,
    val cantidad: Int,
    val precio: Double,
    @Serializable(with = LocalDateTimeSerializer::class) val createdAt: LocalDateTime = LocalDateTime.now(),
    @Serializable(with = LocalDateTimeSerializer::class) val updatedAt: LocalDateTime = LocalDateTime.now()
)