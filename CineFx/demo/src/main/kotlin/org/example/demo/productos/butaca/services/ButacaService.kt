package org.example.demo.productos.butaca.services

import com.github.michaelbull.result.Result
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.models.Butaca
import java.io.File

interface ButacaService {
    fun getAll(): Result<List<Butaca>, ButacaError>
    fun getByTipo(tipo: String): Result<List<Butaca>, ButacaError>
    fun getById(id: String): Result<Butaca, ButacaError>
    fun getByEstado(estado: String): Result<List<Butaca>, ButacaError>
    fun getByOcupacion(ocupacion: String): Result<List<Butaca>, ButacaError>
    fun create(butaca: Butaca): Result<Butaca, ButacaError>
    fun update(id: String, butaca: Butaca): Result<Butaca, ButacaError>
    fun import(csvFile: File): Result<List<Butaca>, ButacaError>
    fun export(fecha:String,list: List<Butaca>): Result<Unit, ButacaError>
}