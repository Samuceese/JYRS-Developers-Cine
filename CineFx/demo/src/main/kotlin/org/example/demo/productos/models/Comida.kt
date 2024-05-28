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
    tipo:String,
    precio:Double
):Complemento(id,tipo,precio) {


}
enum class CategoriaComida{
    PALOMITAS,
    FRUTOSSECOS,
    PATATAS
}
