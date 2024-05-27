package org.example.demo.productos.butaca.services

import com.github.michaelbull.result.Result
import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Ocupacion
import java.io.File
import java.io.InputStream

interface ButacaService {
    fun getAll(): Result<List<Butaca>, ButacaError>
    fun getByTipo(tipo: String): Result<List<Butaca>, ButacaError>
    fun getById(id: String): Result<Butaca, ButacaError>
    fun getByEstado(estado: String): Result<List<Butaca>, ButacaError>
    fun getByOcupacion(ocupacion: String): Result<List<Butaca>, ButacaError>
    fun create(butaca: Butaca): Result<Butaca, ButacaError>
    fun update(id: String, butaca: Butaca, ocupacion: Ocupacion, precio:Double): Result<Butaca, ButacaError>
    fun deleteAll()
    fun import(csvFile: File): Result<List<Butaca>, ButacaError>
    fun export(fecha:String,list: List<Butaca>): Result<Long, ButacaError>
}