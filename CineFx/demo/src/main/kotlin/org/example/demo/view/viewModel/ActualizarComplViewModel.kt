package org.example.demo.view.viewModel

import com.github.michaelbull.result.mapBoth
import javafx.beans.property.SimpleObjectProperty
import org.example.demo.productos.complementos.services.ComplementoService
import org.example.demo.productos.models.Bebida
import org.example.demo.productos.models.Comida
import org.example.demo.productos.models.Complemento
import org.example.demo.view.viewModel.GestionComplementoViewModel.ComplementoState

class ActualizarComplViewModel(
    private val service:ComplementoService
) {
    val state: SimpleObjectProperty<ActualizarComplementoState> = SimpleObjectProperty(ActualizarComplementoState())

    fun actualizar(id:String,tipo:String,precio:String){
        service.update(
            id = id,
            complemento =when(tipo) {
                "COMIDA"->Comida(id,tipo,precio.toDouble())
                "BEBIDA"->Bebida(id,tipo,precio.toDouble())
                else->Comida(id,tipo,precio.toDouble())
            }
        )
    }


    fun allComplementos():List<Complemento>{
        return service.getAll().value
    }


    data class ActualizarComplementoState(
        val tipos:List<String> = listOf("COMIDA","BEBIDA")
    )

}