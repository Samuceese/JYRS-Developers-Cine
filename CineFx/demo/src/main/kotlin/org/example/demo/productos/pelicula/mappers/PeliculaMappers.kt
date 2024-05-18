package org.example.demo.productos.pelicula.mappers

import database.ButacaEntity
import database.PeliculaEntity
import org.example.demo.productos.models.Pelicula

fun PeliculaEntity.toPelicula():Pelicula{
    return Pelicula(
        id = this.id,
        nombre = this.nombre
    )
}

fun Pelicula.toPeliculaEntity():PeliculaEntity{
    return PeliculaEntity(
        id = this.id,
        nombre=this.nombre
    )
}