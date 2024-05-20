package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.Hyperlink
import javafx.scene.control.PasswordField
import javafx.scene.control.TextField
import javafx.scene.image.ImageView
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging


private val logger= logging()
class LoginViewController {

    @FXML
    lateinit var nombreLogin: TextField
    @FXML
    lateinit var contraseñaLogin:PasswordField
    @FXML
    lateinit var botonLogin:Button
    @FXML
    lateinit var botonRegister:Button
    @FXML
    lateinit var olvidaContraseñaLink:Hyperlink
    @FXML
    lateinit var imagenUser:ImageView
    @FXML
    lateinit var imagenUsers:ImageView
    @FXML
    lateinit var imagenKey:ImageView
    @FXML
    lateinit var imagenLogo:ImageView

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller login" }
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        botonLogin.setOnAction { botonLoginOnAction() }
        botonRegister.setOnAction { botonRegisterOnAction() }
        olvidaContraseñaLink.setOnAction { linkOlvidarContraseñaOnAction() }
    }

    private fun linkOlvidarContraseñaOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.OLVIDARCONTRA, title = "Recuperar Contraseña")
    }

    private fun botonRegisterOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.REGISTRO, title = "Registro")
    }

    private fun botonLoginOnAction() {
        if (nombreLogin.text == "admin"){
            RoutesManager.changeScene(view = RoutesManager.View.MENUADMIN, title = "Menu Admin")
        }else {
            RoutesManager.changeScene(view = RoutesManager.View.SELECPELICULAS, title = "Seleccionar Pelicula")
        }
    }

}