package org.example.demo.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import org.example.database.AppDatabase
import org.example.demo.config.Config
import org.example.demo.database.SqlDelightManager.databaseQueries
import org.lighthousegames.logging.logging


private val logger = logging()
/**
 * Para manejar la base de datos.
 * @property databaseQueries
 * @author Yahya El Hadri, Samuel Cortés, Raúl Fernández, Javier Hernández
 * @since 1.0
 */

object SqlDelightManager {
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    /**
     * Inicia el gestor de base de datos.
     * @author Yahya El Hadri, Samuel Cortés, Raúl Fernández, Javier Hernández
     * @since 1.0
     */

    init {
        logger.debug { "Inicializando el gestor de Base de Datos con SqlDelight" }
        initialize()
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

    /**
     * Inicializa.
     * @author Yahya El Hadri, Samuel Cortés, Raúl Fernández, Javier Hernández
     * @since 1.0
     */


    fun initialize() {
        if (Config.databaseInitData) {
            initilize()
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
            databaseQueries.deleteAllButacaEntity()
            databaseQueries.deleteAllComplemetoEntity()
            databaseQueries.deleteAllClientes()
            databaseQueries.InsertTheAdmin()
        }
    }
}