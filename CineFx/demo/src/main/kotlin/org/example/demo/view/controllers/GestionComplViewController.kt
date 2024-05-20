package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import org.example.demo.productos.models.Complemento
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging


private val logger= logging()
class GestionComplViewController {
    @FXML
    lateinit var menuAdmin: ImageView
    @FXML
    lateinit var imagenComplemento :ImageView
    @FXML
    lateinit var tipoTextfield: TextField
    @FXML
    lateinit var nombreTextfield: TextField
    @FXML
    lateinit var precioTextfield: TextField
    @FXML
    lateinit var añadirButton:Button
    @FXML
    lateinit var modificarButton: Button
    @FXML
    lateinit var eliminarButton: Button
    @FXML
    lateinit var filtroNombre:TextField
    @FXML
    lateinit var filtroTipo:ComboBox<String>
    @FXML
    lateinit var tablaComplementos:TableView<Complemento>
    @FXML
    lateinit var nombreColumna:TableColumn<Complemento,String>
    @FXML
    lateinit var tipoColumna:TableColumn<Complemento,String>
    @FXML
    lateinit var precioColumna:TableColumn<Complemento,String>
    @FXML
    lateinit var filtroPrecio:ComboBox<String>

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Gestion de Complementos" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        menuAdmin.setOnMouseClicked { menuOnAction() }
        añadirButton.setOnAction { añadirOnAction()  }
        modificarButton.setOnAction { modificarOnAction() }
    }

    private fun añadirOnAction() {
        RoutesManager.initDetalle(view = RoutesManager.View.NEWCOMPL, title = "Añadir Complemento")
    }

    private fun modificarOnAction() {
        RoutesManager.initDetalle(view = RoutesManager.View.ACTUALIZARCOMPL, title = "Actualizar Complemento")
    }

    private fun menuOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.MENUADMIN, title = "Menu Admin")
    }

}