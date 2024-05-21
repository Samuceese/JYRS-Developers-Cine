package org.example.demo.venta.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.demo.locale.toDefaultMoneyString
import org.example.demo.venta.errors.VentaError
import org.example.demo.venta.models.Venta
import org.lighthousegames.logging.logging
import java.io.File

private val logger = logging()
class VentaStorageHtml:VentasStorage {
    override fun export(venta: Venta, file: File,pelicula:String): Result<Unit, VentaError> {
        return try {
            val html = """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Venta</title>
                </head>
                <body style="font-family: system-ui, -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, Oxygen, Ubuntu, Cantarell, 'Open Sans', 'Helvetica Neue', sans-serif;">
                    <div>
                        <h1>Venta</h1>
                        <p>Fecha: ${venta.createdAt}</p>
                        <p>Cliente: ${venta.cliente.nombre}</p>
                        <p>Pelcula:$pelicula</p>
                        <p>Productos:</p>
                        <table border="2px">
                            <thead>
                                <tr>
                                    <th>Nombre</th>
                                    <th>Cantidad</th>
                                    <th>Precio Unitario</th>
                                    <th>Cantidad</th>
                                    <th>Precio Total</th>
                                </tr>
                            </thead>
                            <tbody>
                                ${
                venta.lineas.forEach {
                    "<tr>" +
                            "<td>${it.id}</td>" +
                            "<td>${it.precio.toDefaultMoneyString()}</td>" +
                            "<td>${it.cantidad}</td>" +
                            "<td>${(it.cantidad * it.precio).toDefaultMoneyString()}</td>" +
                            "</tr>"
                }
            }
                            </tbody>
                        </table>
                        <p>Total: <span style="font-weight: bold;">${venta.total.toDefaultMoneyString()}</span></p>
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
}