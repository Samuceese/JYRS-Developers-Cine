package org.example.demo.productos.pelicula.mappers

import database.ButacaEntity
import database.PeliculaEntity
import org.example.demo.productos.models.Pelicula

/**
 * Mapea un PeliculaEntity en una pelicula.
 * @return pelicula
 * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
 * @since 1.0
 */

fun PeliculaEntity.toPelicula():Pelicula{
    return Pelicula(
        id = this.id,
        nombre = this.nombre
    )
}

/**
 * Mapea una Pelicula en PeliculaEntity.
 * @return PeliculaEntity
 * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
 * @since 1.0
 */

fun Pelicula.toPeliculaEntity():PeliculaEntity{
    return PeliculaEntity(
        id = this.id,
        nombre=this.nombre
    )
}