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
        if (estado == Estado.MANTENIMIENTO ){
            ocupacion = Ocupacion.INACTIVA
        }else if(estado == Estado.OCUPADA){
            ocupacion=Ocupacion.OCUPADA
        }else{
            ocupacion= Ocupacion.LIBRE
        }
    }

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