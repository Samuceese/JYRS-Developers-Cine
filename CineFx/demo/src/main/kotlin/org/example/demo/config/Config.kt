package org.example.demo.config

import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()

/**
 * Clase config
 * @author Yahya El Hadri, Raúl Fernández, Javier Hernández, Samuel Cortés
 * @since 1.0
 */

object Config {
    var databaseUrl: String = "jdbc:sqlite:cine.db"
        private set
    var databaseInitTables: Boolean = false
        private set
    var databaseInitData: Boolean = false
        private set
    var databaseInMemory: Boolean = false
        private set
    var storageData: String = "data"
        private set
    var cacheSize: Int = 5
        private set

}