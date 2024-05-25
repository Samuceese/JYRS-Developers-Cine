package org.example.demo.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import org.example.database.AppDatabase
import org.example.demo.config.Config

import org.lighthousegames.logging.logging
import java.io.File
import java.nio.file.Files
import kotlin.math.log


private val logger = logging()

/**
 * Para manejar la base de datos.
 * @property databaseQueries
 * @author Yahya El Hadri, Samuel Cortés, Raúl Fernández, Javier Hernández
 * @since 1.0
 */

class SqlDelightManager(
    private val config: Config
) {
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    init {
        logger.debug { "Inicializando la base de datos con SqlDelight" }
        clearData()
        initilize()
    }

    /**
     * Inicia el gestor de base de datos.
     * @return databaseQueries
     * @author Yahya El Hadri, Samuel Cortés, Raúl Fernández, Javier Hernández
     * @since 1.0
     */

    private fun initQueries(): DatabaseQueries {

        return if (Config.databaseInMemory) {
            logger.debug { "SqlDeLightClient - InMemory" }
            JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        } else {
            logger.debug { "SqlDeLightClient - File: ${Config.databaseUrl}" }
            JdbcSqliteDriver(Config.databaseUrl)
        }.let { driver ->
            // Creamos la base de datos
            logger.debug { "Creando Tablas (si es necesario)" }
            AppDatabase.Schema.create(driver)
            AppDatabase(driver)
        }.databaseQueries
    }


    fun clearData() {
        logger.debug { "Borrando datos de la base de datos" }
        databaseQueries.transaction {
            databaseQueries.deleteAllUsers()
            databaseQueries.deleteAllButacaEntity()
            databaseQueries.deleteAllComplemetoEntity()
            databaseQueries.removeAllVentas()
            databaseQueries.removeAllLineaVentaEntityButaca()
            databaseQueries.removeAllLineaVentaEntityComplemento()
        }
    }

    /**
     * Inicia el gestor de base de datos.
     * @return databaseQueries
     * @author Yahya El Hadri, Samuel Cortés, Raúl Fernández, Javier Hernández
     * @since 1.0
     */

    private fun initilize() {
        logger.debug { "SqlDeLightClient.removeAllData()" }
        databaseQueries.transaction {
            databaseQueries.InsertTheAdmin()
        }
    }
}