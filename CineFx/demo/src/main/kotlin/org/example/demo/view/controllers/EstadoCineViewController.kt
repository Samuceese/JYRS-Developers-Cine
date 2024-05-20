package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.image.ImageView
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging

private val logger= logging()
class EstadoCineViewController {
    @FXML
    lateinit var menuAdmin:ImageView

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Estado Cine" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        menuAdmin.setOnMouseClicked { menuOnAction() }
    }

    private fun menuOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.MENUADMIN, title = "Menu Admin")
    }
}