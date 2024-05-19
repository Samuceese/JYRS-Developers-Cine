package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Hyperlink
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging

private val logger= logging()
class MenuAdminViewController {

    @FXML
    lateinit var gestionButacas :Hyperlink
    @FXML
    lateinit var gestionComplementos :Hyperlink
    @FXML
    lateinit var estadoCine :Hyperlink
    @FXML
    lateinit var botonCerrarSesion:Button

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Menu Admin" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        gestionComplementos.setOnAction { complemenetosOnAction() }
        botonCerrarSesion.setOnAction { cerrarSesionOnAction() }

    }

    private fun cerrarSesionOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.INICIO_SESION, title = "Inicio de Sesion6")
    }

    private fun complemenetosOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.GESTIONCOMPL, title = "Gestion De Complementos")
    }

}