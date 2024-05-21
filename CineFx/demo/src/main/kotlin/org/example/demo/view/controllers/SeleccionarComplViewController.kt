package org.example.demo.view.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.Spinner
import javafx.scene.control.TableColumn
import javafx.scene.control.TableView
import javafx.scene.control.TextField
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.ImageView
import org.example.demo.productos.models.Complemento
import org.example.demo.routes.RoutesManager
import org.example.demo.view.viewModel.SeleccionarAsientoViewModel
import org.example.demo.view.viewModel.SeleccionarComplViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.lighthousegames.logging.logging


private val logger = logging()
class SeleccionarComplViewController :KoinComponent{

    val viewButacas:SeleccionarAsientoViewModel by inject()
    val viewCompl:SeleccionarComplViewModel by inject()

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
        initReactiveProperties()
        initDefaultValues()
    }

    private fun initDefaultValues() {
        cantidadSpinner.setMaxSize(0.0,3.0)
        tablaComplementos.items=
            FXCollections.observableList(viewCompl.state.value.complementos)
        nombreColumna.cellValueFactory = PropertyValueFactory("id")
        tipoColumna.cellValueFactory = PropertyValueFactory("tipo")
        precioColumna.cellValueFactory = PropertyValueFactory("precio")
    }

    private fun initReactiveProperties() {
        viewCompl.state.addListener { _,_,newValue->
            tipoTextfield.text=newValue.tipo
            nombreTextfield.text=newValue.nombre
            precioTextfield.text=newValue.precio
        }
    }

    private fun initDefaultEvents() {
        volverButton.setOnAction { volverOnAction() }
        continuarButton.setOnAction { continuarOnAction() }
        tablaComplementos.selectionModel.selectedItemProperty().addListener { _,_,newValue->
            newValue?.let { onTableSelected(it) }
        }
    }

    private fun onTableSelected(complemento: Complemento) {
        viewCompl.updateState(complemento)
    }

    private fun continuarOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.CARRITO, title = "Carrito")
    }

    private fun volverOnAction(){
        viewCompl.borrarSeleccionado()
        viewButacas.reasignarButacas()
        viewButacas.state.value.butacasSeleccionadas.clear()
        RoutesManager.changeScene(view = RoutesManager.View.SELECBUTACAS, title = "Seleccionar Butaca", height = 650.00)
    }

}