package org.example.demo.productos.complementos.errors

sealed class ComplementoError(val mensaje: String) {
    class IdNoValido(mensage: String): ComplementoError(mensage)
    class TipoInvalido(mensage: String): ComplementoError(mensage)
    class FechaInvalido(mensage: String): ComplementoError(mensage)
    class FicheroNoValido(mensage: String): ComplementoError(mensage)
    class ComplementoNoEncontrado(mensage: String): ComplementoError(mensage)
    class ComplementoNoBorrado(mensage: String): ComplementoError(mensage)
    class ComplementoNoActualizado(mensage: String): ComplementoError(mensage)
}