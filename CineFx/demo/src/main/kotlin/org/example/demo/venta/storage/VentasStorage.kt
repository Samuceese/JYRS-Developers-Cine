package org.example.demo.venta.storage

import com.github.michaelbull.result.Result
import org.example.demo.venta.errors.VentaError
import org.example.demo.venta.models.Venta
import java.io.File

interface VentasStorage {
    fun export(venta: Venta, file: File): Result<Unit, VentaError>
}