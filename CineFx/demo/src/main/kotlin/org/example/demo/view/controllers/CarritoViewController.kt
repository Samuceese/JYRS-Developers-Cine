package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Label
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging


private val logger=logging()
class CarritoViewController {

    @FXML
    lateinit var productoLabel1: Label
    @FXML
    lateinit var productoLabel2: Label
    @FXML
    lateinit var productoLabel3: Label
    @FXML
    lateinit var productoLabel4: Label
    @FXML
    lateinit var productoLabel5: Label
    @FXML
    lateinit var productoLabel6: Label
    @FXML
    lateinit var productoLabel7: Label
    @FXML
    lateinit var productoLabel8: Label
    @FXML
    lateinit var precioLabel1: Label
    @FXML
    lateinit var precioLabel2: Label
    @FXML
    lateinit var precioLabel3: Label
    @FXML
    lateinit var precioLabel4: Label
    @FXML
    lateinit var precioLabel5: Label
    @FXML
    lateinit var precioLabel6: Label
    @FXML
    lateinit var precioLabel7: Label
    @FXML
    lateinit var precioLabel8: Label
    @FXML
    lateinit var botonContinuar:Button
    @FXML
    lateinit var botonVolver:Button

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Carrito" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        botonVolver.setOnAction { volverOnAction() }
        botonContinuar.setOnAction { continuarOnAction() }
    }

    private fun continuarOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.PAGO, title = "Pagar")
    }

    private fun volverOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.SELECCOMPL, title = "Seleccionar Complemento")
    }

}