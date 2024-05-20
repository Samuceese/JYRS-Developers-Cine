package org.example.demo.productos.models

import java.time.LocalDate

class Butaca(
    val id:String,
    var estado: Estado,
    var tipo:Tipo,
    val create: LocalDate = LocalDate.now()
):Producto() {
    var precio:Double
    var ocupacion: Ocupacion
    init {
        when(this.tipo){
            Tipo.VIP-> precio=8.0
            Tipo.NORMAL->precio=5.0
        }
        if (estado == Estado.MANTENIMIENTO){
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
}

enum class Ocupacion{
    LIBRE,
    SELECCIONADA,
    OCUPADA,
    INACTIVA
}
enum class Tipo{
    NORMAL,
    VIP
}