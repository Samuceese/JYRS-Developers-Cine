package org.example.demo.view.viewModel

import javafx.beans.property.SimpleObjectProperty
import org.example.demo.view.viewModel.SeleccionarAsientoViewModel.ButacasState

class SeleccionarPeliculaViewModel {

    val state: SimpleObjectProperty<PeliculaState> = SimpleObjectProperty(PeliculaState())

    data class PeliculaState(
        val pelicula:String=""
    )
}