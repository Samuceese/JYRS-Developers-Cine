package org.example.demo.config

import org.lighthousegames.logging.logging
import kotlin.io.path.Path

private val logger = logging()
/**
 * clase config
 * @author Yahya el hadri el bakkali
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