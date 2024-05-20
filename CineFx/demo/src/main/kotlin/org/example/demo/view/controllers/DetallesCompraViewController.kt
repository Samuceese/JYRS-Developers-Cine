package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import javafx.scene.image.ImageView
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging


private val logger= logging()
class DetallesCompraViewController {

    @FXML
    lateinit var imagenMenu: ImageView
    @FXML
    lateinit var nombreLabel: Label
    @FXML
    lateinit var correoLabel: Label
    @FXML
    lateinit var tituloLabel: Label
    @FXML
    lateinit var complementosLabel: Label
    @FXML
    lateinit var fxButacaLabel: Label
    @FXML
    lateinit var fxTotalLabel: Label
    @FXML
    lateinit var fxBotonCerrarSesion:Button
    @FXML
    lateinit var fxBotonSeleccionarPelicula:Button
    @FXML
    lateinit var fxBotonImprimirPdf:Button

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Detalles de compra" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        imagenMenu.setOnMouseClicked { menuOnAction() }
        fxBotonSeleccionarPelicula.setOnAction { menuOnAction() }
        fxBotonCerrarSesion.setOnAction { cerrarSesionOnAction() }
    }

    private fun cerrarSesionOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.INICIO_SESION, title = "Inicio De Sesion")
    }

    private fun menuOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.SELECPELICULAS, title = "Seleccionar Pelicula")
    }


}