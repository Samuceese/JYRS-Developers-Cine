package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Spinner
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import org.example.demo.productos.models.Complemento
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging


private val logger = logging()
class SeleccionarComplViewController {
    @FXML
    lateinit var tipoTextfield: TextField
    @FXML
    lateinit var nombreTextfield: TextField
    @FXML
    lateinit var precioTextfield: TextField
    @FXML
    lateinit var cantidadSpinner: Spinner<Int>
    @FXML
    lateinit var añadirComplementoButton:Button
    @FXML
    lateinit var imagenComplemento: ImageView
    @FXML
    lateinit var volverButton:Button
    @FXML
    lateinit var continuarButton: Button
    @FXML
    lateinit var filtroNombre:TextField
    @FXML
    lateinit var filtroTipo:ComboBox<String>
    @FXML
    lateinit var tablaComplementos:TableView<Complemento>
    @FXML
    lateinit var nombreColumna: TableColumn<Complemento,String>
    @FXML
    lateinit var tipoColumna: TableColumn<Complemento,String>
    @FXML
    lateinit var precioColumna: TableColumn<Complemento,String>
    @FXML
    lateinit var filtroPrecio:ComboBox<String>

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller seleccionar Complemento" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        volverButton.setOnAction { volverOnAction() }
        continuarButton.setOnAction { continuarOnAction() }
    }

    private fun continuarOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.CARRITO, title = "Carrito")
    }

    private fun volverOnAction(){
        RoutesManager.changeScene(view = RoutesManager.View.SELECBUTACAS, title = "Seleccionar Butaca", height = 650.00)
    }

}