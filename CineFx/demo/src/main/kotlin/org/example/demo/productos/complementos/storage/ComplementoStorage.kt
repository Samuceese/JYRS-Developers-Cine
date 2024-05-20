package org.example.demo.productos.complementos.storage

import com.github.michaelbull.result.Result
import org.example.demo.productos.complementos.errors.ComplementoError
import org.example.demo.productos.models.Complemento
import java.io.File

interface ComplementoStorage {
    fun load(file: File): Result<List<Complemento>, ComplementoError>
}