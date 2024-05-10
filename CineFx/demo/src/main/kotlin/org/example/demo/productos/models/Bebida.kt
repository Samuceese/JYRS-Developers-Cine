package org.example.demo.productos.models

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