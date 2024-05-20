package org.example.demo

import javafx.application.Application
import javafx.stage.Stage
import org.example.demo.routes.RoutesManager

class CineApplication : Application() {
    override fun start(stage: Stage) {
        RoutesManager.apply {
            app=this@CineApplication
        }
        RoutesManager.initMainStage(stage)
    }
}

fun main() {
    Application.launch(CineApplication::class.java)
}