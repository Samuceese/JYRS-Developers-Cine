package org.example.demo.productos.models

import kotlinx.serialization.Serializable
import org.example.demo.LocalDateTimeSerializer
import java.time.LocalDate

/**
 * Representa una butaca.
 * @property id
 * @property estado
 * @property tipo
 * @property create
 * @author Raúl Fernández, Javier Hernández, Samuel Cortés, Yahya El Hadri.
 * @since 1.0
 */
@Serializable
class Butaca(
    val id:String,
    var estado: Estado,
    var tipo:Tipo,
    @Serializable(with = LocalDateTimeSerializer::class) val create: LocalDate = LocalDate.now(),
    var precio:Double,
    var ocupacion: Ocupacion
):Producto() {

    override fun toString(): String {
        return "$id, $tipo: $precio €"
    }
}
enum class Estado {
    ACTIVA,
    MANTENIMIENTO,
    OCUPADA,
}

enum class Ocupacion{
    LIBRE,
    SELECCIONADA,
    INACTIVA,
    OCUPADA
}
enum class Tipo{
    NORMAL,
    VIP
}