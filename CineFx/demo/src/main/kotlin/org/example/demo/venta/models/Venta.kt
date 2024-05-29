package org.example.demo.venta.models

import kotlinx.serialization.Serializable
import org.example.demo.LocalDateTimeSerializer
import org.example.demo.UUIDSerializer
import org.example.demo.usuarios.models7.Usuario
import java.time.LocalDateTime
import java.util.*

@Serializable
data class Venta(
    @Serializable(with = UUIDSerializer::class) val id: UUID = UUID.randomUUID(),
    val cliente: Usuario,
    val lineas: List<LineaVenta>,
    @Serializable(with = LocalDateTimeSerializer::class) val createdAt: LocalDateTime = LocalDateTime.now(),
    @Serializable(with = LocalDateTimeSerializer::class) val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    val total: Double
        get() = lineas.sumOf { it.precio  }

    override fun toString(): String {
        return "Venta(id=$id, cliente=$cliente, lineas=$lineas, createdAt=$createdAt, updated=$updatedAt, total=$total)"
    }
}