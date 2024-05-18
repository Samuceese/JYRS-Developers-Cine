package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging

private val logger= logging()
class PagoViewController {

    @FXML
    lateinit var menuImagen: ImageView
    @FXML
    lateinit var fxNombreTextField: TextField
    @FXML
    lateinit var fxNumeroTextField: TextField
    @FXML
    lateinit var fxFechaTextField: TextField
    @FXML
    lateinit var fxCvcTextField: TextField
    @FXML
    lateinit var fxPagarButton: Button

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller pago" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        menuImagen.setOnMouseClicked { menuOnAction() }
        fxPagarButton.setOnAction { pagarOnAction() }
    }

    private fun pagarOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.TICKET, title = "Detalle de tu compra")
    }

    private fun menuOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.CARRITO, title = "Carrito")
    }


}