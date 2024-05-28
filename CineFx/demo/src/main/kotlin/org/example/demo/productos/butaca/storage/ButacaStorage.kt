package org.example.demo.productos.butaca.storage

import com.github.michaelbull.result.Result
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.models.Butaca
import java.io.File

interface ButacaStorage {
    fun saveJson(file: File, list: List<Butaca>): Result<Unit, ButacaError>
    fun loadJson(file: File): Result<List<Butaca>, ButacaError>

    fun saveCsv(file: File, list: List<Butaca>): Result<Unit, ButacaError>
    fun loadCsv(file: File): Result<List<Butaca>, ButacaError>
}