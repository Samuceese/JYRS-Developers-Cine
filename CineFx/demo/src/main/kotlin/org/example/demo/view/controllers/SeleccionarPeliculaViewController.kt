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
        simioImage.setOnMouseClicked { simioOnAction() }
        garfieldImage.setOnMouseClicked { garfieldOnAction() }
        tarotImage.setOnMouseClicked { tarotOnAction() }
        especialistaImage.setOnMouseClicked { especialistaOnAction() }
    }

    private fun simioOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.SELECBUTACAS, title = "Seleccion Butaca Simio", height = 650.00)
    }

    private fun garfieldOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.SELECBUTACAS, title = "Seleccion Butaca Garfield", height = 650.00)
    }

    private fun tarotOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.SELECBUTACAS, title = "Seleccion Butaca Tarot", height = 650.00)
    }

    private fun especialistaOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.SELECBUTACAS, title = "Seleccion Butaca Especialista", height = 650.00)
    }


    private fun initDefaultValues() {
        simioImage.image = Image(RoutesManager.getResourceAsStream("images/simios.jpeg"))
        garfieldImage.image = Image(RoutesManager.getResourceAsStream("images/garfield.jpeg"))
        tarotImage.image = Image(RoutesManager.getResourceAsStream("images/TAROT.jpg"))
        especialistaImage.image = Image(RoutesManager.getResourceAsStream("images/especialista.jpeg"))
    }
}