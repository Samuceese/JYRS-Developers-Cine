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
        if (state.value.butacas.size == 0){
            service.import(RoutesManager.getResourceAsStream("data/butacas.csv")).onSuccess {
                initState(it)
            }
        }
    }

    fun reasignarButacas() {
        state.value.butacasSeleccionadas.forEach {
            actualizarButaca(it.id,Estado.ACTIVA, it.tipo, it.create, Ocupacion.LIBRE, it.precio)
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
    ):Butaca{
        val butaca = Butaca(id,estado, tipo, createAt)
        return service.update(id,butaca,ocupada,precio).value
    }
    fun actualizarButacasSeleccionadas(lista:MutableList<Butaca>){
        state.value = state.value.copy(
            butacasSeleccionadas = lista
        )
    }

    fun actualizarButacas(){
        val lista= mutableListOf<Butaca>()
        state.value.butacas.forEach {
            if (verificarButaca(it.id)){
                lista.add(actualizarButaca(it.id,Estado.OCUPADA,it.tipo,it.create,Ocupacion.OCUPADA,it.precio))
            }else{
                lista.add(it)
            }
        }
        state.value = state.value.copy(
            butacasSeleccionadas = mutableListOf(),
            butacas = lista
        )
    }

    private fun verificarButaca(id:String):Boolean{
        state.value.butacasSeleccionadas.forEach {
            if (it.id == id) return true
        }
        return false
    }



    data class ButacasState(
        var butacas:MutableList<Butaca> = mutableListOf(),
        val butacasSeleccionadas:MutableList<Butaca> = mutableListOf()
    )
}