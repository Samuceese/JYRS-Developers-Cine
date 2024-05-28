package org.example.demo.view.controllers

import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import org.example.demo.routes.RoutesManager
import org.example.demo.view.viewModel.ActualizarComplViewModel
import org.example.demo.view.viewModel.GestionComplementoViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.lighthousegames.logging.logging

private val logger= logging()
class ActualizarComplViewController:KoinComponent {

    val view:ActualizarComplViewModel by inject()
    val viewGest:GestionComplementoViewModel by inject()

    @FXML
    lateinit var fxTextFieldPrecio:TextField
    @FXML
    lateinit var fxtextFieldBebida:TextField
    @FXML
    lateinit var fxTextFieldNombre:TextField
    @FXML
    lateinit var fxBotonGuardar:Button
    @FXML
    lateinit var fxBotonLimpiar:Button
    @FXML
    lateinit var fxBotonCancelar:Button

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Actualizar complemento" }
        initDefaultEvents()
        initDefaulValues()
    }

    private fun initDefaulValues() {
        fxTextFieldNombre.text=viewGest.state.value.complementoSeleccionado!!.id
        fxtextFieldBebida.text=viewGest.state.value.complementoSeleccionado!!.tipo
        fxTextFieldPrecio.text=viewGest.state.value.complementoSeleccionado!!.precio.toString()
    }

    private fun initDefaultEvents() {
        fxBotonGuardar.setOnAction { guardarOnAction() }
        fxBotonLimpiar.setOnAction { limpiarOnAction() }
    }

    private fun limpiarOnAction() {
        fxTextFieldPrecio.text=""
    }

    private fun guardarOnAction() {
        if (fxTextFieldNombre.text.isBlank())RoutesManager.alerta("Nombre","Recuerda que el nombre no puede estar vacio")
        if (fxTextFieldPrecio.text.isBlank())RoutesManager.alerta("Precio","Recuerda que el precio no puede estar vacio")
        if (fxTextFieldPrecio.text.toDoubleOrNull() == null)RoutesManager.alerta("Precio","Recuerda que el precio debe ser un double 0.0")
        if (fxTextFieldPrecio.text.isNotBlank() &&
            fxTextFieldNombre.text.isNotBlank() &&
            fxTextFieldPrecio.text.toDoubleOrNull()!= null){
            view.actualizar(id = fxTextFieldNombre.text, precio = fxTextFieldPrecio.text, tipo = fxtextFieldBebida.text)
            viewGest.initState(view.allComplementos())
        }
    }
}