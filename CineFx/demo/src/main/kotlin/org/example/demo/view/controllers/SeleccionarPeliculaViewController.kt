package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging


private val logger= logging()
class SeleccionarPeliculaViewController {

    @FXML
    lateinit var simioImage: ImageView
    @FXML
    lateinit var garfieldImage:ImageView
    @FXML
    lateinit var tarotImage:ImageView
    @FXML
    lateinit var especialistaImage:ImageView

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller Seleccionar Pelicula" }
        initDefaultValues()
        initDefaultEvents()
    }

    private fun initDefaultEvents() {
        simioImage.setOnMouseClicked { imagenesOnAction() }
        garfieldImage.setOnMouseClicked { imagenesOnAction() }
        tarotImage.setOnMouseClicked { imagenesOnAction() }
        especialistaImage.setOnMouseClicked { imagenesOnAction() }
    }

    private fun imagenesOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.SELECBUTACAS, title = "Seleccion Butaca", height = 650.00)
    }

    private fun initDefaultValues() {
        simioImage.image = Image(RoutesManager.getResourceAsStream("images/simios.jpeg"))
        garfieldImage.image = Image(RoutesManager.getResourceAsStream("images/garfield.jpeg"))
        tarotImage.image = Image(RoutesManager.getResourceAsStream("images/TAROT.jpg"))
        especialistaImage.image = Image(RoutesManager.getResourceAsStream("images/especialista.jpeg"))
    }
}