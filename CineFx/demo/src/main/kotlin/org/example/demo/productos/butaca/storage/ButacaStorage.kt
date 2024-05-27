package org.example.demo.productos.butaca.storage

import com.github.michaelbull.result.Result
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.models.Butaca
import java.io.File
import java.io.InputStream

interface ButacaStorage {
    fun save(file: File,list: List<Butaca>): Result<Long, ButacaError>
    fun load(file: File): Result<List<Butaca>, ButacaError>
}