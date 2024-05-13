package org.example.demo.productos.butaca.errors

sealed class ButacaError(val mensage:String) {
    class IdNoValido(mensage: String):ButacaError(mensage)
    class TipoInvalido(mensage: String):ButacaError(mensage)
    class FechaInvalido(mensage: String):ButacaError(mensage)
    class FicheroNoValido(mensage: String):ButacaError(mensage)
    class ButacaNoEncontrada(mensage: String):ButacaError(mensage)
    class ButacaNoBorradas(mensage: String):ButacaError(mensage)
    class ButacaNoActualizadas(mensage: String):ButacaError(mensage)
}