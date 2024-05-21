package org.example.demo.productos.butaca.storage

import com.github.michaelbull.result.Result
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.models.Butaca
import java.io.File
import java.io.InputStream

interface ButacaStorage {
    fun save(fecha:String,list: List<Butaca>): Result<Unit, ButacaError>
    fun load(file: InputStream): Result<List<Butaca>, ButacaError>
}