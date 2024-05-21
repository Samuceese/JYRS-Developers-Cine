package org.example.demo.view.viewModel

import com.github.michaelbull.result.onSuccess
import javafx.beans.property.SimpleObjectProperty
import org.example.demo.productos.butaca.services.ButacaService
import org.example.demo.productos.models.Butaca
import org.example.demo.productos.models.Estado
import org.example.demo.productos.models.Ocupacion
import org.example.demo.productos.models.Tipo
import org.example.demo.routes.RoutesManager

import java.time.LocalDate



class SeleccionarAsientoViewModel(
    private val service: ButacaService
) {
    val state: SimpleObjectProperty<ButacasState> = SimpleObjectProperty(ButacasState())

    init {
        service.import(RoutesManager.getResourceAsStream("data/butacas.csv")).onSuccess {
            initState(it)
        }
    }

    fun importarCsv(){
        service.deleteAll()
        state.value.butacas.clear()
        service.import(RoutesManager.getResourceAsStream("data/butacas.csv")).onSuccess {
            initState(it)
        }
    }

    private fun initState(it: List<Butaca>) {
        state.value = state.value.copy(
            butacas = it.toMutableList()
        )
    }

    fun buscarButacaId(id:String):Butaca?{
        service.getById(id).onSuccess { return it }
        return null
    }

    fun actualizarButaca(
        id: String,
        estado: Estado,
        tipo: Tipo,
        createAt: LocalDate,
        ocupada: Ocupacion,
        precio: Double
    ){
        val butaca = Butaca(id,estado, tipo, createAt)
        service.update(id,butaca,ocupada,precio)
    }
    fun actualizarButacasSeleccionadas(lista:MutableList<Butaca>){
        state.value = state.value.copy(
            butacasSeleccionadas = lista
        )
    }


    data class ButacasState(
        var butacas:MutableList<Butaca> = mutableListOf(),
        val butacasSeleccionadas:MutableList<Butaca> = mutableListOf()
    )
}