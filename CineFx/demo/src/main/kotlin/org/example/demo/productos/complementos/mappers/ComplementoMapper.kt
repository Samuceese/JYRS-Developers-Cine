package org.example.demo.productos.complementos.mappers

import database.ComplementoEntity
import org.example.demo.productos.complementos.dto.ComplementoDto
import org.example.demo.productos.complementos.exceptions.ComplementoException
import org.example.demo.productos.models.*

fun ComplementoEntity.toComplemento(): Complemento {
    val _nombre: String = this.nombre
    val _tipo: String = this.tipo
    when (_tipo) {
        "COMIDA" -> {
            when (_nombre) {
                "PALOMITAS" -> return Comida("PALOMITAS",CategoriaComida.PALOMITAS)
                "FRUTOSSECOS" -> return Comida("FRUTOS SECOS",CategoriaComida.FRUTOSSECOS)
                "PATATAS" -> return Comida("PATATAS", CategoriaComida.PATATAS)
            }
        }

        "BEBIDA" -> {
            when (_nombre) {
                "AGUA" -> return Bebida("AGUA", CategoriaBebida.AGUA)
                "REFRESCO" -> return Bebida("REFRESCO",CategoriaBebida.REFRESCOS)
            }
        }

    }
    throw ComplementoException.TipoInvalido("Tipo no 2valido")
}

fun ComplementoDto.toComplemento(): Complemento {
    when(this.nombre){
        "PALOMITAS" -> return Comida("PALOMITAS",CategoriaComida.PALOMITAS)
        "FRUTOS SECOS" -> return Comida("FRUTOS SECOS",CategoriaComida.FRUTOSSECOS)
        "PATATAS" -> return Comida("PATATAS",CategoriaComida.PATATAS)
        "AGUA" -> return Bebida("AGUA",CategoriaBebida.AGUA)
        "REFRESCO" -> return Bebida("REFRESCO",CategoriaBebida.REFRESCOS)
    }
    throw ComplementoException.TipoInvalido("Tipo no valido")
}