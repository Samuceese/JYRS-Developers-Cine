package org.example.demo.productos.complementos.services

import com.github.michaelbull.result.Result
import org.example.demo.productos.complementos.errors.ComplementoError
import org.example.demo.productos.models.Complemento
import java.io.File

interface ComplementoService {
    fun getAll(): Result<List<Complemento>, ComplementoError>
    fun getByTipo(tipo: String): Result<List<Complemento>, ComplementoError>
    fun getById(id: String): Result<Complemento, ComplementoError>
    fun create(complemento: Complemento): Result<Complemento, ComplementoError>
    fun update(id: String, complemento: Complemento): Result<Complemento, ComplementoError>
    fun delete(id: String): Result<Complemento, ComplementoError>
    fun import(csvFile: File): Result<List<Complemento>, ComplementoError>
}