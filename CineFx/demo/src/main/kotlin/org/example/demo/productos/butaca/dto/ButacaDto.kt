package org.example.demo.productos.butaca.dto

import kotlinx.serialization.Serializable

@Serializable
class ButacaDto(
    val id:String,
    val estado: String,
    val tipo: String,
    val precio:String,
    var ocupacion: String,
    var createAt: String,
)