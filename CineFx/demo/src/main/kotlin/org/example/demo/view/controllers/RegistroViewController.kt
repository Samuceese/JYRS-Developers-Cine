package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging

private val logger= logging()
class RegistroViewController {
    @FXML
    lateinit var nombreRegister: TextField
    @FXML
    lateinit var botonRegister: Button
    @FXML
    lateinit var contraseñaRegister: PasswordField
    @FXML
    lateinit var emailRegister: TextField
    @FXML
    lateinit var contraseñaConfirmarRegister: PasswordField

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller register" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        botonRegister.setOnAction { botonRegisterOnAction() }
    }

    private fun botonRegisterOnAction(){
        RoutesManager.changeScene(view = RoutesManager.View.INICIO_SESION, title = "Inicio Sesion")
    }
}