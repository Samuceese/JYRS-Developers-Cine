package org.example.demo.routes

import javafx.application.Application
import javafx.application.Platform
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.control.Alert
import javafx.scene.control.ButtonType
import javafx.scene.image.Image
import javafx.scene.layout.Pane
import javafx.stage.Modality
import javafx.stage.Stage
import javafx.stage.WindowEvent
import java.io.InputStream
import java.lang.RuntimeException
import java.net.URL


object RoutesManager {
    lateinit var mainStage:Stage
    lateinit var _activeStage:Stage
    val activeStage:Stage
        get() = _activeStage
    lateinit var app:Application

    enum class View(val fxml:String){
        INICIO_SESION("views/inicio-sesion.fxml"),
        REGISTRO("views/registro.fxml"),
        SELECPELICULAS("views/selec-peliculas.fxml"),
        SELECBUTACAS("views/selec-butacas.fxml"),
        SELECCOMPL("views/selec-compl.fxml"),
        CARRITO("vies/carrito.fxml"),
        PAGO("views/pago.fxml"),
        TICKET("views/ticket.fxml"),
        ACERCA_DE("views/acerca-de.fxml")
    }
    
    fun initMainStage(stage:Stage){
        val fxmlLoader=FXMLLoader(getResource(View.REGISTRO.fxml))
        val parentRoot = fxmlLoader.load<Pane>()
        val mainScene = Scene(parentRoot,700.0,500.0)

        stage.apply {
            title="Inicio Sesion"
            isResizable=false
            scene=mainScene
            _activeStage=stage
            icons.add(Image(getResourceAsStream("icons/jyrs-cinema.png")))
            setOnCloseRequest { onAppExit(event = it) }
            centerOnScreen()
        }.show()
    }

    fun changeScene(
        myStage: Stage = activeStage,
        view: View,
        title:String,
        width: Double = 700.0,
        height: Double = 500.0,
    ) {
        val parentRoot = FXMLLoader.load<Pane>(this.getResource(view.fxml))
        val scene = Scene(parentRoot, width, height)
        myStage.scene = scene
        myStage.title= title
    }

    fun initAcercaDeStage(){
        val fxmlLoader=FXMLLoader(getResource(View.ACERCA_DE.fxml))
        val parentRoot=fxmlLoader.load<Pane>()
        val mainScene=Scene(parentRoot,400.0,200.0)
        Stage().apply {
            title="Acerca de"
            scene=mainScene
            isResizable=false
            initOwner(mainStage)
            initModality(Modality.WINDOW_MODAL)
            centerOnScreen()
            icons.add(Image(getResourceAsStream("icons/informacion.png")))
        }.show()
    }


    fun onAppExit(
        title:String="Salir de ${mainStage.title}",
        headerText:String="Va a salir de ${mainStage.title}, ¿estas seguro?",
        contentText:String="Si sale, perdera la informacion no guardada",
        event: WindowEvent?=null
    ) {
        Alert(Alert.AlertType.CONFIRMATION).apply {
            this.title=title
            this.contentText=contentText
            this.headerText=headerText
        }.showAndWait().ifPresent {
            if(it== ButtonType.OK) Platform.exit()
            else event?.consume()
        }

    }

    fun getResourceAsStream(icon: String): InputStream {
        return app::class.java.getResourceAsStream(icon)
            ?:throw RuntimeException("No se a podido cargar $icon")

    }

    fun getResource(fxml: String): URL{
        return app::class.java.getResource(fxml)
            ?:throw RuntimeException("No se a podido cargar $fxml")
    }
}