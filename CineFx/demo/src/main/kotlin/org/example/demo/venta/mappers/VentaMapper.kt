package org.example.demo.venta.mappers

import database.LineaVentaEntityButaca
import database.LineaVentaEntityComplemento
import database.SelectAllLineasById
import database.VentaEntity
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Complemento
import org.example.demo.usuarios.models7.Usuario
import org.example.demo.venta.models.LineaVenta
import org.example.demo.venta.models.Venta
import java.time.LocalDateTime
import java.util.*

fun LineaVentaEntityButaca.toLineaVenta(butaca:Butaca):LineaVenta{
    return LineaVenta(
        id = UUID.fromString(this.id),
        producto = butaca,
        cantidad = this.cantidad.toInt(),
        precio = this.precio,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at)
    )
}

fun LineaVentaEntityComplemento.toLineaVenta(complemento:Complemento):LineaVenta{
    return LineaVenta(
        id = UUID.fromString(this.id),
        producto = complemento,
        cantidad = this.cantidad.toInt(),
        precio = this.precio,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at)
    )
}

fun VentaEntity.toVenta(cliente: Usuario, lineas:List<LineaVenta>):Venta{
    return Venta(
        id = UUID.fromString(this.id),
        cliente = cliente,
        lineas = lineas,
        createdAt = LocalDateTime.parse(this.created_at),
        updatedAt = LocalDateTime.parse(this.updated_at),
    )
}