package org.example.demo.productos.models

/**
 * Representa a un admin.
 * @property id
 * @property nombre
 * @author Raúl Fernández, Javier Hernández, Samuel Cortés, Yahya El Hadri.
 * @since 1.0
 */

class Comida(
    id:String,
    val nombre:CategoriaComida
):Complemento(id) {
    val precio:Double
    init {
        when(nombre){
            CategoriaComida.PALOMITAS-> precio=3.0
            CategoriaComida.FRUTOSSECOS->precio=2.0
            CategoriaComida.PATATAS-> precio=2.5
        }
    }

    override fun toString(): String {
        return "$nombre: $precio €"
    }
}
enum class CategoriaComida{
    PALOMITAS,
    FRUTOSSECOS,
    PATATAS
}
