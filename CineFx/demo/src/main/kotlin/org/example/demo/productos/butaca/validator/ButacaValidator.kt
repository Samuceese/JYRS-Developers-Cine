package org.example.demo.productos.butaca.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.demo.productos.butaca.dto.ButacaDto
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.models.Butaca

class ButacaValidator {
    fun validarFecha(fecha:String):Boolean{
        if (fecha.length != 10) return false
        val fechaSeparada= fecha.split("/")
        if (fechaSeparada[0].length != 4) return false
        if (fechaSeparada[1].length != 2) return false
        if (fechaSeparada[2].length != 2) return false
        if (fechaSeparada[2].toInt() !in 1..31) return false
        if (fechaSeparada[1].toInt() !in 1..12) return false
        return true
    }
    fun validarButaca(butaca:Butaca): Result<Butaca, ButacaError> {
        return when {
            !validarId(butaca.id) -> Err(ButacaError.IdNoValido("El ID: ${butaca.id} no es valido, debe ser Letra Número: Ej A2"))
            else -> Ok(butaca)
        }
    }

    fun validarButacaDto(butaca: ButacaDto): Result<ButacaDto, ButacaError>{
        return when{
            !validarId(butaca.id) -> Err(ButacaError.IdNoValido("El ID: ${butaca.id} no es valido, debe ser Letra Número: Ej A2"))
            !validarEstado(butaca) -> Err(ButacaError.EstadoNoValido("El estado de la butaca no es válido debe ser ACTIVA o MANTENIMIENTO"))
            !validarOcupación(butaca) -> Err(ButacaError.OcupacionNoValiado("La ocupación de la butaca no es válida debe ser LIBRE, SELECCIONADA, OCUPADA O INACTIVA"))
            !validarTipo(butaca) -> Err(ButacaError.TipoInvalido("El tipo de la butaca no es válido, debe ser NORMAL o VIP"))
            !esDouble(butaca.precio) -> Err(ButacaError.PrecioNoValido("El precio de la butaca no es válido, debe ser un double"))
            else -> Ok(butaca)
        }
    }
    fun esDouble(cadena: String): Boolean {
        return try {
            cadena.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }
    private fun validarOcupación(butaca: ButacaDto): Boolean{
        return if(butaca.ocupacion != "LIBRE" && butaca.ocupacion != "SELECCIONADA" && butaca.ocupacion != "OCUPADA" && butaca.ocupacion != "INACTIVA"){
            false
        }else{
            true
        }
    }
    private fun validarEstado(butaca: ButacaDto): Boolean{
        if (butaca.estado != "ACTIVA" || butaca.estado != "MANTENIMIENTO"){
            return false
        }
        return true
    }

    private fun validarTipo(butaca: ButacaDto): Boolean{
        if(butaca.tipo != "NORMAL" || butaca.tipo != "VIP"){
            return false
        }
        return true
    }

    private fun validarId(id: String) :Boolean{
        if (id.length != 2) return false
        val letra=id.slice(0..0)
        val letras= arrayOf("A","B","C","D","E")
        if (letra.uppercase() !in letras) return false
        val numero= id.slice(1..1).toIntOrNull()?: -1
        if (numero == -1)return false
        if (numero !in 1..7) return false
        return true
    }
}