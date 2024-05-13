package org.example.demo.productos.butaca

import org.example.demo.productos.butaca.dto.ButacaDto
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Estado
import org.example.demo.productos.models.Ocupacion
import org.example.demo.productos.models.Tipo

/*fun ButacaEntity.toButaca():Butaca{
    var _id=this.id
    var _estado:Estado?=null
    var _tipo:Tipo?=null
    when(this.tipo){
        "NORMAL" -> _tipo = Tipo.NORMAL
        "VIP"-> _tipo = Tipo.VIP
    }
    when(this.estado){
        "ACTIVA" -> _estado = Estado.ACTIVA
        "MANTENIMIENTO"-> _estado=Estado.MANTENIMIENTO
        "OUTSERVICE"-> _estado=Estado.OUTSERVICE
    }
    return Butaca(_id,_estado!!,_tipo!!,LocalDate.parse(this.createAt))

}

 */
/**
 * El mapper de la butacaDto a una butaca normal
 * @return Butaca
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
fun ButacaDto.toButaca():Butaca{
    var _tipo:Tipo?=null
    when(this.tipo){
        "VIP"->_tipo = Tipo.VIP
        "NORMAL" -> _tipo = Tipo.NORMAL
    }
    var _estado:Estado?=null
    when(this.estado) {
        "ACTIVA" -> _estado = Estado.ACTIVA
        "MANTENIMIENTO" -> _estado = Estado.MANTENIMIENTO
        "OUTSERVICE" -> _estado = Estado.OUTSERVICE
    }
    return Butaca(this.id,_estado!!,_tipo!!)
}
/**
 * El mapper de la butaca normal a butacaDto
 * @return ButacaDto
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
fun Butaca.toButacaDto(): ButacaDto {
    var _tipo:String?=null
    when(this.tipo){
        Tipo.VIP -> _tipo ="VIP"
        Tipo.NORMAL -> _tipo = "NORMAL"
    }
    var _estado:String?=null
    when(this.estado){
        Estado.ACTIVA -> _estado = "ACTIVA"
        Estado.MANTENIMIENTO -> _estado = "MANTENIMIENTO"
        Estado.OUTSERVICE-> _estado = "OUTSERVICE"
    }
    var _ocupacion:String?=null
    when(this.ocupacion){
        Ocupacion.LIBRE-> _ocupacion = "LIBRE"
        Ocupacion.INACTIVA-> _ocupacion = "INACTIVA"
        Ocupacion.RESERVA-> _ocupacion = "RESERVA"
        Ocupacion.OCUPADA-> _ocupacion = "OCUPADA"
    }
    return ButacaDto(this.id,_estado,_tipo,this.precio.toString(),_ocupacion,this.create.toString())
}