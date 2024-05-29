package org.example.demo.view.viewModel

import com.github.michaelbull.result.mapBoth
import javafx.beans.property.SimpleObjectProperty
import org.example.demo.productos.complementos.services.ComplementoService
import org.example.demo.productos.models.Bebida
import org.example.demo.productos.models.Comida
import org.example.demo.productos.models.Complemento
import org.example.demo.venta.mappers.logger
import org.example.demo.view.viewModel.GestionComplementoViewModel.ComplementoState
import java.io.File

class ActualizarComplViewModel(
    private val service:ComplementoService
) {
    val state: SimpleObjectProperty<ActualizarComplementoState> = SimpleObjectProperty(ActualizarComplementoState())

    fun actualizar(id:String,tipo:String,precio:String, imagen: String){
        service.update(
            id = id,
            complemento =when(tipo) {
                "COMIDA"->Comida(id,tipo,precio.toDouble(), imagen )
                "BEBIDA"->Bebida(id,tipo,precio.toDouble(), imagen)
                else->Comida(id,tipo,precio.toDouble(), imagen)
            }
        )
    }

    fun updateImageComplemento(fileImage: File){
        logger.debug { "Actualizando imagen: $fileImage" }
        state.value = state.value.copy(

        )
    }


    fun allComplementos():List<Complemento>{
        return service.getAll().value
    }


    data class ActualizarComplementoState(
        val tipos:List<String> = listOf("COMIDA","BEBIDA")
    )

}