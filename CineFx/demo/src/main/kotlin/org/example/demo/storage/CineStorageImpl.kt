package org.example.demo.storage

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Complemento
import org.example.demo.usuarios.models7.Usuario
import org.example.demo.venta.models.Venta
import java.io.File
import com.github.michaelbull.result.Result
import org.example.demo.errors.ErrorStorage
import org.example.demo.productos.butaca.storage.ButacaStorage
import org.example.demo.productos.complementos.storage.ComplementoStorage
import org.example.demo.usuarios.storage.UsuarioStorage
import org.example.demo.venta.storage.VentasStorage
import org.lighthousegames.logging.logging
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream

private val logger= logging()

class CineStorageImpl  (
    private val storageButacas: ButacaStorage,
    private val storageComplementos:ComplementoStorage,
    private val storageClientes: UsuarioStorage,
    private val storageVentas: VentasStorage
): CineStorage {
    private val dirName = "JYRSCinemaZip"

    override fun exportToZip(
        file: File,
        butacas: List<Butaca>,
        complementos:List<Complemento>,
        usuarios: List<Usuario>,
        ventas: List<Venta>
    ): Result<File, ErrorStorage> { //dataVentas: List<Venta>
        logger.debug { "Exportando a ZIP $file" }
        val tempDir = Files.createTempDirectory(dirName)
        return try {

            storageButacas.saveJson(File("$tempDir","butacas.json"), butacas)
            Files.walk(tempDir).forEach { logger.debug { it } }
            val allButacas = Files.walk(tempDir)
                .filter { Files.isRegularFile(it) }
                .toList()
            ZipOutputStream(Files.newOutputStream(file.toPath())).use { zip ->
                allButacas.forEach { butacas ->
                    val entradaZip = ZipEntry(tempDir.relativize(butacas).toString())
                    zip.putNextEntry(entradaZip)
                    Files.copy(butacas, zip)
                    zip.closeEntry()
                }
            }


            storageComplementos.saveJson(File("$tempDir","complementos.json"), complementos)
            Files.walk(tempDir).forEach { logger.debug { it } }
            val allComplementos = Files.walk(tempDir)
                .filter { Files.isRegularFile(it) }
                .toList()
            ZipOutputStream(Files.newOutputStream(file.toPath())).use { zip ->
                allComplementos.forEach { complementos ->
                    val entradaZip = ZipEntry(tempDir.relativize(complementos).toString())
                    zip.putNextEntry(entradaZip)
                    Files.copy(complementos, zip)
                    zip.closeEntry()
                }
            }


            storageClientes.storeJson(File("$tempDir","clientes.json"), usuarios)
            Files.walk(tempDir)
                .forEach { logger.debug { it } }
            val allUsuarios = Files.walk(tempDir)
                .filter { Files.isRegularFile(it) }
                .toList()
            ZipOutputStream(Files.newOutputStream(file.toPath())).use { zip ->
                allUsuarios.forEach { usuarios ->
                    val entradaZip = ZipEntry(tempDir.relativize(usuarios).toString())
                    zip.putNextEntry(entradaZip)
                    Files.copy(usuarios, zip)
                    zip.closeEntry()
                }
            }
            storageVentas.save(File("$tempDir","ventas.json"), ventas)
            Files.walk(tempDir).forEach { logger.debug { it } }
            val allVentas = Files.walk(tempDir)
                .filter { Files.isRegularFile(it) }
                .toList()
            ZipOutputStream(Files.newOutputStream(file.toPath())).use { zip ->
                allVentas.forEach { venta ->
                    val entradaZip = ZipEntry(tempDir.relativize(venta).toString())
                    zip.putNextEntry(entradaZip)
                    Files.copy(venta, zip)
                    zip.closeEntry()
                }
            }

            logger.debug { "Exportado con exito" }
            Ok(file)
        } catch (e: Exception) {
            logger.error { "Error al exportar a ZIP: ${e.message}" }
            Err(ErrorStorage.FicheroError("Error al exportar a ZIP: ${e.message}"))
        }
    }
    override fun loadFromZip(file: File): Result<List<Any>, ErrorStorage> {
        logger.debug { "Importando desde ZIP $file" }
        val tempDir = Files.createTempDirectory(dirName)
        return try {
            ZipFile(file).use { zip ->
                zip.entries().asSequence().forEach { entrada ->
                    zip.getInputStream(entrada).use { input ->
                        Files.copy(
                            input,
                            Paths.get(tempDir.toString(), entrada.name),
                            StandardCopyOption.REPLACE_EXISTING
                        )
                    }
                }
            }
            val listaButacas = storageButacas.loadJson(File("$tempDir","butacas.json"))
            val listaComplementos = storageComplementos.loadJson(File("$tempDir","complementos.json"))
            val listaUusarios = storageClientes.loadJson(File("$tempDir","clientes.json"))
            val listaVentas = storageVentas.load(File("$tempDir","ventas.json"))
            val listado: MutableList<Any> = mutableListOf()
            listaUusarios.value.forEach { listado.add(it) }
            listaComplementos.value.forEach { listado.add(it) }
            listaButacas.value.forEach { listado.add(it) }
            listaVentas.value.forEach { listado.add(it) }
            tempDir.toFile().deleteRecursively()
            return Ok(listado.toList())
        } catch (e: Exception) {
            logger.error { "Error al importar desde ZIP: ${e.message}" }
            Err(ErrorStorage.FicheroError("Error al importar desde ZIP: ${e.message}"))
        }
    }


}