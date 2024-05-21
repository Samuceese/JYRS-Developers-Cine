package org.example.demo.productos.butaca.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.models.Butaca

/**
 * Validamos una cadena de texto que representa una fecha.
 * @param fecha
 * @return Devuelve true si cumple con el formato deseado.
 * @author Raúl Fernández, Yahya El Hadri, Samuel Cortés, Javier Hernández
 * @since 1.0
 */


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

    /**
     * Validamos butacas.
     * @param butaca
     * @return Devuelve butaca si se valida y si no ButacaError si hay errores.
     * @author Raúl Fernández, Yahya El Hadri, Samuel Cortés, Javier Hernández
     * @since 1.0
     */

    fun validarButaca(butaca:Butaca): Result<Butaca, ButacaError> {
        when {
            !validarId(butaca.id) -> Err(ButacaError.IdNoValido("El ID: ${butaca.id} no es valido"))
            else -> Ok(butaca)
        }
        return Ok(butaca)
    }

    /**
     * Validamos el id.
     * @param id
     * @return Devuelve un boolean que indica si el ID es válido o no.
     * @author Raúl Fernández, Yahya El Hadri, Samuel Cortés, Javier Hernández
     * @since 1.0
     */

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