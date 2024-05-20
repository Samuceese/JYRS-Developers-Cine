package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging

private val logger= logging()
class OlvidarContraViewController {

    @FXML
    lateinit var textEmail: TextField
    @FXML
    lateinit var newPassword: PasswordField
    @FXML
    lateinit var botonRestablecer: Button
    @FXML
    lateinit var confirmPassword: PasswordField

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller olvidar contraseña" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        botonRestablecer.setOnAction { restablecerOnAction() }
    }

    private fun restablecerOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.INICIO_SESION, title = "Inicio Sesion")
    }
}