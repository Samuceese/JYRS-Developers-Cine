package org.example.demo.productos.butaca.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.demo.productos.butaca.dto.ButacaDto
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.models.Butaca
import org.koin.core.qualifier.qualifier
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

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
        if(!validarId(butaca.id)){
            return Err(ButacaError.IdNoValido("El id no es válido, debe ser letra número EJ: A2"))
        }
        if(validarOcupación(butaca).isErr){
            return Err(ButacaError.OcupacionNoValiado("La ocupación de la butaca no es válida, debe ser LIBRE, SELECCIONADA, OCUPADA o INACTIVA"))
        }
        if(!esDouble(butaca.precio)){
            return Err(ButacaError.PrecioNoValido("El precio de la butaca no es válido"))
        }
        if(validarEstado(butaca).isErr){
            return Err(ButacaError.EstadoNoValido("El estado de la butaca es incorrecto, debe ser ACTIVA o MANTENIMIENTO"))
        }
        if(validarTipo(butaca).isErr){
            return Err(ButacaError.TipoNoValido("El tipo de la butaca debe ser NORMAL o VIP"))
        }
        if(!validateFechaForDTo(butaca.createAt)){
            return Err(ButacaError.FechaInvalido("La fecha introducida no es en el formato correcto Ej: yyyy-MM-dd"))
        }
        return Ok(butaca)
    }

    fun validateFechaForDTo(fecha: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return try {
            LocalDate.parse(fecha, formatter)
            true
        } catch (e: DateTimeParseException) {
            false
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
    private fun validarOcupación(butaca: ButacaDto): Result<ButacaDto, ButacaError.OcupacionNoValiado> {
        return if(butaca.ocupacion !in listOf("LIBRE", "SELECCIONADA", "OCUPADA", "INACTIVA")){
            Err(ButacaError.OcupacionNoValiado("La ocupación de la butaca no es válida, debe ser LIBRE, SELECCIONADA, OCUPADA o INACTIVA"))
        }else{
            Ok(butaca)
        }
    }
    private fun validarEstado(butaca: ButacaDto): Result<ButacaDto, ButacaError.EstadoNoValido> {
        return if(butaca.estado !in listOf("ACTIVA", "MANTENIMIENTO")){
            Err(ButacaError.EstadoNoValido("El estado de la butaca es incorrecto, debe ser ACTIVA o MANTENIMIENTO"))
        }else{
            Ok(butaca)
        }
    }

    private fun validarTipo(butaca: ButacaDto): Result<ButacaDto, ButacaError.TipoNoValido> {
        return if(butaca.tipo !in listOf("NORMAL", "VIP")){
            Err(ButacaError.TipoNoValido("El tipo de la butaca debe ser NORMAL o VIP"))
        }else{
            Ok(butaca)
        }
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