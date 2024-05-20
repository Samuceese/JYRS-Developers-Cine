package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging

private val logger= logging()
class ModificarButacaViewController {
    @FXML
    lateinit var textId:TextField
    @FXML
    lateinit var textPrecio:TextField
    @FXML
    lateinit var comboTipo:ComboBox<String>
    @FXML
    lateinit var comboEstado:ComboBox<String>
    @FXML
    lateinit var comboOcupacion:ComboBox<String>
    @FXML
    lateinit var botonGuardar:Button
    @FXML
    lateinit var botonLimpiar:Button
    @FXML
    lateinit var botonCancelar:Button

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Modificar Butaca" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        botonGuardar.setOnAction { guaradarOnAction() }
    }

    private fun guaradarOnAction() {
        RoutesManager.cerrarVentana()
    }
}