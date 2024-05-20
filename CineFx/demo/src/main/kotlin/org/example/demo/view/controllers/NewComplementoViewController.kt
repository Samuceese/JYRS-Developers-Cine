package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging

private val logger= logging()
class NewComplementoViewController {
    @FXML
    lateinit var fxTextFieldPrecio:TextField
    @FXML
    lateinit var fxtextFieldBebida:ComboBox<String>
    @FXML
    lateinit var fxTextFieldNombre:TextField
    @FXML
    lateinit var fxBotonCrear:Button
    @FXML
    lateinit var fxBotonLimpiar:Button
    @FXML
    lateinit var fxBotonCancelar:Button

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Nuevo Complementos" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        fxBotonCrear.setOnAction { crearOnAction() }
    }

    private fun crearOnAction() {

    }
}