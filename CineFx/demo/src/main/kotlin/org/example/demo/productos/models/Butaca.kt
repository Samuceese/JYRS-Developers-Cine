package org.example.demo.productos.models

import java.time.LocalDate

class Butaca(
    val id:String,
    var estado: Estado,
    val tipo:Tipo,
    val create: LocalDate = LocalDate.now()
):Producto() {
    val precio:Double
    var ocupacion: Ocupacion
    init {
        when(this.tipo){
            Tipo.VIP-> precio=8.0
            Tipo.NORMAL->precio=5.0
        }
        if (estado == Estado.MANTENIMIENTO || estado== Estado.OUTSERVICE){
            ocupacion = Ocupacion.INACTIVA
        }else{
            ocupacion=Ocupacion.LIBRE
        }
    }

    override fun toString(): String {
        return "$id, $tipo: $precio €"
    }
}
enum class Estado {
    ACTIVA,
    MANTENIMIENTO,
    OUTSERVICE
}

enum class Ocupacion{
    LIBRE,
    RESERVA,
    OCUPADA,
    INACTIVA
}
enum class Tipo{
    NORMAL,
    VIP
}