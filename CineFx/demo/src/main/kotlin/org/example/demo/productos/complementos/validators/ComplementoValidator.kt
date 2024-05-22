package org.example.demo.productos.complementos.validators

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.demo.productos.complementos.dto.ComplementoDto
import org.example.demo.productos.complementos.errors.ComplementoError
import org.example.demo.productos.models.Complemento

fun validateComplemento(complemento: ComplementoDto): Result<ComplementoDto, ComplementoError> {
    if (!validateTipo(complemento.tipoComplemento)) {
        return Err(ComplementoError.ValidationError("Tipo incorrecto, debe Ser COMIDA o BEBIDA"))
    }
    if (!validateNombre(complemento.nombre)) {
        return Err(ComplementoError.ValidationError("El nombre es incorrecto debe ser PALOMITAS, FRUTOS SECOS, PATATAS, AGUA, REFRESCOS"))
    }
    if (!esDouble(complemento.precio)) {
        return Err(ComplementoError.ValidationError("El precio no es válido"))
    }
    if (!validateConcordanciaComida(complemento.tipoComplemento, complemento.nombre)) {
        return Err(ComplementoError.ValidationError("El tipo de complemento es comida y se está introduciendo una bebida"))
    }
    if(!validateConcordanciaBebida(complemento.tipoComplemento, complemento.nombre)){
        return Err(ComplementoError.ValidationError("EL tipo de complemento es bebida y se está introduciendo comida"))
    }
    return Ok(complemento)
}


    private fun validateConcordanciaComida(tipo: String, nombre: String): Boolean{
        if (tipo == "COMIDA" && nombre in listOf("AGUA", "REFRESCO")){
            return false
        }
        return true
    }

private fun validateConcordanciaBebida(tipo: String, nombre: String): Boolean{
    if (tipo == "BEBIDA" && nombre in listOf("PALOMITAS", "PATATAS", "FRUTOS SECOS")){
        return false
    }
    return true
}

    private fun validateTipo(tipo: String): Boolean {
        if (tipo !in listOf("COMIDA", "BEBIDA") || tipo.isEmpty()) {
            return false
        }
        return true
    }

    private fun validateNombre(nombre: String): Boolean {
        if (nombre !in listOf("PALOMITAS", "FRUTOS SECOS", "PATATAS", "AGUA", "REFRESCO") || nombre.isEmpty()) {
            return false
        }
        return true
    }

    fun esDouble(cadena: String): Boolean {
        return try {
            cadena.toDouble()
            true
        } catch (e: NumberFormatException) {
            false
        }
    }