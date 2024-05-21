package org.example.demo.productos.complementos.storage.images

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import org.example.demo.config.Config
import org.example.demo.productos.complementos.errors.ComplementoError
import org.lighthousegames.logging.logging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.time.Instant
import com.github.michaelbull.result.Result
import java.nio.file.StandardCopyOption

private val logger = logging()

class StorageImageImpl(
    private val config: Config
): StorageImages {
    init {
        try {
            val imagesPath = Paths.get(config.imagenesDirectory)
            if (Files.notExists(imagesPath)) {
                Files.createDirectories(imagesPath)
                logger.debug { "Carpeta de imágenes creada en: ${config.imagenesDirectory}" }
            }
        } catch (e: Exception) {
            logger.error { "Error al crear la carpeta de imágenes: ${e.message}" }
        }
    }

    private fun getImageName(newFileImage: File): String {
        val name = newFileImage.name
        val extension = name.substring(name.lastIndexOf(".") + 1)
        return "${Instant.now().toEpochMilli()}.$extension"
    }

    override fun saveImage(fileName: File): Result<File, ComplementoError> {
        logger.debug { "Guardando imagen $fileName" }
        return try {
            val newFileName = getImageName(fileName)
            val newFileImage = Paths.get(config.imagenesDirectory, newFileName).toFile()
            Files.copy(fileName.toPath(), newFileImage.toPath(), StandardCopyOption.REPLACE_EXISTING)
            Ok(newFileImage)
        }catch (e : Exception){
            Err(ComplementoError.ComplememntoImageError("Error al guardar la imagen: ${e.message}"))
        }
    }

    override fun loadImage(fileName: String): Result<File, ComplementoError> {
        logger.debug { "Cargando imagen $fileName" }
        val file = Paths.get(config.imagenesDirectory, fileName).toFile()
        return if(file.exists()){
            Ok(file)
        }else{
            Err(ComplementoError.ComplememntoImageError("La imagen no existe: ${file.name}"))
        }
    }

    override fun deleteImage(fileName: File): Result<Unit, ComplementoError> {
        return try {
            Files.deleteIfExists(fileName.toPath())
            Ok(Unit)
        }catch (e : Exception){
            Err(ComplementoError.ComplememntoImageError("Error al eliminar la imagen: ${e.message}"))
        }
    }

    override fun deleteAllImages(): Result<Long, ComplementoError> {
        logger.debug { "Borrando todas las imagenes" }
        return try{
            Ok(Files.walk(Paths.get(config.imagenesDirectory))
                .filter{Files.isRegularFile(it)}
                .map { Files.deleteIfExists(it) }
                .count())
        }catch(e: Exception){
            Err(ComplementoError.ComplememntoImageError("Error al borrar todas las imagenes: ${e.message}"))
        }
    }

    override fun updateImage(imageName: String, newFileImage: File): Result<File, ComplementoError> {
        logger.debug { "Actualizando imagen $imageName" }
        return try {
            val newUpdateImage = Paths.get(config.imagenesDirectory, imageName).toFile()
            Files.copy(newFileImage.toPath(), newUpdateImage.toPath(), StandardCopyOption.REPLACE_EXISTING)
            Ok(newUpdateImage)
        }catch (e : Exception){
            Err(ComplementoError.ComplememntoImageError("Error al guardar la imagen: ${e.message}"))
        }
    }

}