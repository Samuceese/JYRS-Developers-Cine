package org.example.demo.view.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import javafx.stage.Stage
import org.example.demo.routes.RoutesManager
import org.example.demo.view.viewModel.GestionComplementoViewModel
import org.example.demo.view.viewModel.NewComplementoViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.lighthousegames.logging.logging

private val logger= logging()
class NewComplementoViewController: KoinComponent {
    val view:NewComplementoViewModel by inject()
    val viewGest:GestionComplementoViewModel by inject()

    
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
        initDefaultValues()
    }

    private fun initDefaultValues() {
        fxtextFieldBebida.items = FXCollections.observableList(view.state.value.tipos)
        fxtextFieldBebida.value="COMIDA"
        fxTextFieldNombre.text=""
        fxTextFieldPrecio.text=""
    }

    private fun initDefaultEvents() {
        fxBotonCrear.setOnAction { crearOnAction() }
        fxBotonLimpiar.setOnAction { limpiarOnAction() }
    }

    private fun limpiarOnAction() {
        fxTextFieldNombre.text=""
        fxTextFieldPrecio.text=""
    }
    private fun crearOnAction() {
        if (fxTextFieldNombre.text.isBlank())RoutesManager.alerta("Nombre","Recuerda que el nombre no puede estar vacio")
        if (fxTextFieldPrecio.text.isBlank())RoutesManager.alerta("Precio","Recuerda que el precio no puede estar vacio")
        if (fxTextFieldPrecio.text.toDoubleOrNull() == null)RoutesManager.alerta("Precio","Recuerda que el precio debe ser un double 0.0")
        if (view.comprobarExistencia(fxTextFieldNombre.text))RoutesManager.alerta("Nombre","Ya existe ese complemento")
        if (fxTextFieldPrecio.text.isNotBlank() &&
            fxTextFieldNombre.text.isNotBlank() &&
            fxTextFieldPrecio.text.toDoubleOrNull()!= null&&
            !view.comprobarExistencia(fxTextFieldNombre.text)){
            view.createComplemento(nombre = fxTextFieldNombre.text, precio = fxTextFieldPrecio.text, tipo = fxtextFieldBebida.value)
            viewGest.initState(view.allComplementos())
        }
    }
}