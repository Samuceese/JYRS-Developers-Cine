package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import org.example.demo.productos.models.Butaca
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging

private val logger= logging()
class GestionButacaViewController {
    @FXML
    lateinit var menuAdmin:ImageView
    @FXML
    lateinit var estadoTextfield: TextField
    @FXML
    lateinit var textoTextfield: TextField
    @FXML
    lateinit var precioTextField: TextField
    @FXML
    lateinit var modificarButton: Button
    @FXML
    lateinit var butacaImage:ImageView
    @FXML
    lateinit var idTextField: TextField
    @FXML
    lateinit var ocupacionTextField: TextField
    @FXML
    lateinit var exportarJsonButton: Button
    @FXML
    lateinit var importarButacasButton: Button
    @FXML
    lateinit var filtroEstado:ComboBox<String>
    @FXML
    lateinit var filtroTipo:ComboBox<String>
    @FXML
    lateinit var filtroOcupacion:ComboBox<String>
    @FXML
    lateinit var idButaca:TextField
    @FXML
    lateinit var tablaButacas:TableView<Butaca>
    @FXML
    lateinit var columnaId:TableColumn<Butaca,String>
    @FXML
    lateinit var columnaEstado:TableColumn<Butaca,String>
    @FXML
    lateinit var columnaTipo:TableColumn<Butaca,String>
    @FXML
    lateinit var columnaPrecio:TableColumn<Butaca,String>
    @FXML
    lateinit var columnaOcupación:TableColumn<Butaca,String>

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Gestion de Butacas" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        menuAdmin.setOnMouseClicked { menuOnAction() }
        modificarButton.setOnAction { modificarOnAction() }
    }

    private fun modificarOnAction() {
        RoutesManager.initDetalle(view = RoutesManager.View.MODBUTACA, title = "Menu Admin")
    }

    private fun menuOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.MENUADMIN, title = "Menu Admin")
    }

}