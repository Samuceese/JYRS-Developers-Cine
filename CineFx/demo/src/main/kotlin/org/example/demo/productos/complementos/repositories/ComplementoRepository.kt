package org.example.demo.productos.complementos.repositories

import org.example.demo.productos.models.Complemento

interface ComplementoRepository {
    fun findAll(): List<Complemento>
    fun findById(id: String): Complemento?
    fun findByTipo(tipo: String): List<Complemento>
    fun save(producto: Complemento): Complemento
    fun update(id: String, complemento:Complemento): Complemento?
    fun delete(id: String): Complemento?
}