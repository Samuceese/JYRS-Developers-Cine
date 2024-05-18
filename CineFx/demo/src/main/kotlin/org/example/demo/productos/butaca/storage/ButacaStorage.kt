package org.example.demo.productos.butaca.storage

import com.github.michaelbull.result.Result
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.models.Butaca
import java.io.File

interface ButacaStorage {
    fun save(fecha:String,list: List<Butaca>): Result<Unit, ButacaError>
    fun load(file: File): Result<List<Butaca>, ButacaError>
}