package org.example.demo.productos.models

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
