package org.example.demo.view.controllers

import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import org.example.demo.routes.RoutesManager
import org.lighthousegames.logging.logging


private val logger= logging()
class SeleccionarAsientoViewController {

    @FXML
    lateinit var butacaImage1a:ImageView
    @FXML
    lateinit var butacaImage1b:ImageView
    @FXML
    lateinit var butacaImage1c:ImageView
    @FXML
    lateinit var butacaImage1d:ImageView
    @FXML
    lateinit var butacaImage1e:ImageView
    @FXML
    lateinit var butacaImage1f:ImageView
    @FXML
    lateinit var butacaImage1g:ImageView
    @FXML
    lateinit var butacaImage2a:ImageView
    @FXML
    lateinit var butacaImage2b:ImageView
    @FXML
    lateinit var butacaImage2c:ImageView
    @FXML
    lateinit var butacaImage2d:ImageView
    @FXML
    lateinit var butacaImage2e:ImageView
    @FXML
    lateinit var butacaImage2f:ImageView
    @FXML
    lateinit var butacaImage2g:ImageView
    @FXML
    lateinit var butacaImage3a:ImageView
    @FXML
    lateinit var butacaImage3b:ImageView
    @FXML
    lateinit var butacaImage3c:ImageView
    @FXML
    lateinit var butacaImage3d:ImageView
    @FXML
    lateinit var butacaImage3e:ImageView
    @FXML
    lateinit var butacaImage3f:ImageView
    @FXML
    lateinit var butacaImage3g:ImageView
    @FXML
    lateinit var butacaImage4a:ImageView
    @FXML
    lateinit var butacaImage4b:ImageView
    @FXML
    lateinit var butacaImage4c:ImageView
    @FXML
    lateinit var butacaImage4d:ImageView
    @FXML
    lateinit var butacaImage4e:ImageView
    @FXML
    lateinit var butacaImage4f:ImageView
    @FXML
    lateinit var butacaImage4g:ImageView
    @FXML
    lateinit var butacaImage5a:ImageView
    @FXML
    lateinit var butacaImage5b:ImageView
    @FXML
    lateinit var butacaImage5c:ImageView
    @FXML
    lateinit var butacaImage5d:ImageView
    @FXML
    lateinit var butacaImage5e:ImageView
    @FXML
    lateinit var butacaImage5f:ImageView
    @FXML
    lateinit var butacaImage5g:ImageView
    @FXML
    lateinit var butacaImage6a:ImageView
    @FXML
    lateinit var butacaImage6b:ImageView
    @FXML
    lateinit var butacaImage6c:ImageView
    @FXML
    lateinit var butacaImage6d:ImageView
    @FXML
    lateinit var butacaImage6e:ImageView
    @FXML
    lateinit var butacaImage6f:ImageView
    @FXML
    lateinit var butacaImage6g:ImageView
    @FXML
    lateinit var butacaImage7a:ImageView
    @FXML
    lateinit var butacaImage7b:ImageView
    @FXML
    lateinit var butacaImage7c:ImageView
    @FXML
    lateinit var butacaImage7d:ImageView
    @FXML
    lateinit var butacaImage7e:ImageView
    @FXML
    lateinit var butacaImage7f:ImageView
    @FXML
    lateinit var butacaImage7g:ImageView

    @FXML
    lateinit var botonVolver: Button
    @FXML
    lateinit var botonContinuar :Button

    @FXML
    fun initialize(){
        logger.debug { "inicializando controller seleccionar butaca" }
        initDefaultEvents()
        initDefaultValues()
    }

    private fun initDefaultValues() {
        butacaImage1a.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage1b.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage1c.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage1d.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage1e.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage1f.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage1g.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage2a.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage2b.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage2c.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage2d.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage2e.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage2f.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage2g.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage3a.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage3b.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage3c.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage3d.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage3e.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage3f.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage3g.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage4a.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage4b.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage4c.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage4d.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage4e.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage4f.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage4g.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage5a.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage5b.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage5c.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage5d.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage5e.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage5f.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage5g.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage6a.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage6b.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage6c.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage6d.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage6e.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage6f.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage6g.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage7a.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage7b.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage7c.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage7d.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage7e.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage7f.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
        butacaImage7g.image= Image( RoutesManager.getResourceAsStream("images/libre.png.png"))
    }

    private fun initDefaultEvents() {
        botonVolver.setOnAction { botonVolveronAction() }
        botonContinuar.setOnAction { botonContinuarOnAction() }
    }

    private fun botonContinuarOnAction() {
        RoutesManager.changeScene(view = RoutesManager.View.SELECCOMPL, title = "Seleccionar Complemento")
    }

    private fun botonVolveronAction() {
        RoutesManager.changeScene(view = RoutesManager.View.SELECPELICULAS, title = "Seleccionar pelicula")
    }
}