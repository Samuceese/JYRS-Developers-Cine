package org.example.demo.productos.butaca.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
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
        when {
            !validarId(butaca.id) -> Err(ButacaError.IdNoValido("El ID: ${butaca.id} no es valido"))
            else -> Ok(butaca)
        }
        return Ok(butaca)
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