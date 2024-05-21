package org.example.demo.database

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import database.DatabaseQueries
import org.example.database.AppDatabase
import org.example.demo.config.Config
import org.lighthousegames.logging.logging


private val logger = logging()
/**
 * manejado de la base datos
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */

object SqlDelightManager {
    val databaseQueries: DatabaseQueries by lazy { initQueries() }

    init {
        logger.debug { "Inicializando el gestor de Base de Datos con SqlDelight" }
        initialize()
    }

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

    fun initialize() {
        if (Config.databaseInitData) {
            initilize()
        }
    }
    fun clearData() {
        logger.debug { "Borrando datos de la base de datos" }
        databaseQueries.transaction {
            databaseQueries.deleteAllClientes()
            databaseQueries.deleteAllComplemetoEntity()
            databaseQueries.deleteAllButacaEntity()
        }
    }



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