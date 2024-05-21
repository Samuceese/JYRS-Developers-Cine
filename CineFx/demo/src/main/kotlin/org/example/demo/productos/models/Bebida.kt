package org.example.demo.productos.models

/**
 * Representa una bebida.
 * @property id
 * @property nombre
 * @author Raúl Fernández, Javier Hernández, Samuel Cortés, Yahya El Hadri.
 * @since 1.0
 */

class Bebida(
    id:String,
    val nombre:CategoriaBebida
):Complemento(id) {
    val precio:Double
    init {
        when(nombre){
            CategoriaBebida.REFRESCOS-> precio=3.0
            CategoriaBebida.AGUA->precio=2.0
        }
    }
    override fun toString(): String {
        return "$nombre: $precio-€"
    }
}

enum class CategoriaBebida{
    AGUA,
    REFRESCOS
}