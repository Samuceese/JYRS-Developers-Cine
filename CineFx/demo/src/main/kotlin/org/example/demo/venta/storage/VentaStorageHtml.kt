package org.example.demo.venta.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.example.demo.locale.toDefaultMoneyString
import org.example.demo.productos.complementos.dto.ComplementoDto
import org.example.demo.productos.complementos.errors.ComplementoError
import org.example.demo.productos.complementos.mappers.toComplemento
import org.example.demo.productos.complementos.mappers.toComplementoDto
import org.example.demo.productos.models.Bebida
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Comida
import org.example.demo.productos.models.Complemento
import org.example.demo.venta.errors.VentaError
import org.example.demo.venta.models.Venta
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()
class VentaStorageHtml:VentasStorage {
    override fun export(venta: Venta, file: File,pelicula:String): Result<Unit, VentaError> {
        return try {
            val lineasHtml = venta.lineas.joinToString("") {
                "<tr>" +
                        "<td>${when(it.producto) {
                            is Butaca -> "Butaca: ${it.producto.id}"
                            is Bebida -> "Bebida: ${it.producto.id}"
                            is Comida -> "Comida: ${it.producto.id}"
                            else -> ""
                        }}</td>" +
                        "<td>${it.precio.toDefaultMoneyString()}</td>" +
                        "</tr>"
            }

            val html = """
                    <!DOCTYPE html>
                    <html lang="en">
                    <head>
                        <meta charset="UTF-8">
                        <meta name="viewport" content="width=device-width, initial-scale=1.0">
                        <title>Venta</title>
                        <style>
                            body {
                                font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;
                                background-color: #f4f4f9;
                                color: #333;
                                margin: 0;
                                padding: 20px;
                            }
                            .container {
                                max-width: 800px;
                                margin: 0 auto;
                                background: #fff;
                                padding: 20px;
                                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                                border-radius: 8px;
                            }
                            h1 {
                                color: #4a90e2;
                            }
                            p {
                                line-height: 1.6;
                            }
                            table {
                                width: 100%;
                                border-collapse: collapse;
                                margin-top: 20px;
                            }
                            table, th, td {
                                border: 1px solid #ddd;
                            }
                            th, td {
                                padding: 12px;
                                text-align: left;
                            }
                            th {
                                background-color: #f4f4f9;
                            }
                            tr:nth-child(even) {
                                background-color: #f9f9f9;
                            }
                            .total {
                                font-weight: bold;
                                font-size: 1.2em;
                                color: #e94e77;
                            }
                        </style>
                    </head>
                    <body>
                        <div class="container">
                            <h1>Venta</h1>
                            <p>Fecha: ${venta.createdAt}</p>
                            <p>Cliente: ${venta.cliente.nombre}</p>
                            <p>Película: $pelicula</p>
                            <p>Productos:</p>
                            <table>
                                <thead>
                                    <tr>
                                        <th>Nombre</th>
                                        <th>Precio Unitario</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    $lineasHtml
                                </tbody>
                            </table>
                            <p>Total: <span class="total">${venta.total.toDefaultMoneyString()}</span></p>
                        </div>
                    </body>
                    </html>
                    """.trimIndent()

            Ok(file.writeText(html, Charsets.UTF_8))
        }catch (e: Exception){
            logger.error { "Error al salvar ventas fichero: ${file.absolutePath}, ${e.message}" }
            Err(VentaError.VentaStorageError("Error al salvar ventas a fichero ${file.absolutePath}. ${e.message}"))
        }
    }

    override fun save(file: File, list: List<Complemento>): Result<Unit, ComplementoError> {
        logger.debug { "Guardando ventas en fichero json" }
        return try {
            val json = Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            Ok(file.writeText(json.encodeToString<List<ComplementoDto>>(list.map { it.toComplementoDto() })))
        }catch (e: Exception){
            logger.error { "Error al guardar el fichero json de ventas" }
            Err(ComplementoError.FicheroNoValido("Error al leer el JSON: ${e.message}"))
        }
    }

    override fun load(file: File): Result<List<Complemento>, ComplementoError> {
        logger.debug { "Cargando ventas desde $file" }
        return try {
            val json = Json{
                prettyPrint = true
                ignoreUnknownKeys = true
            }
            Ok(json.decodeFromString<List<ComplementoDto>>(file.readText()).map { it.toComplemento() })
        }catch (e: Exception) {
            Err(ComplementoError.FicheroNoValido("Error al leer el JSON: ${e.message}"))
        }
    }
}