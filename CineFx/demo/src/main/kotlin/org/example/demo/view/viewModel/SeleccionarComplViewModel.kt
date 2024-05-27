package org.example.demo.view.viewModel

import com.github.michaelbull.result.onSuccess
import javafx.beans.property.SimpleObjectProperty
import javafx.scene.image.Image
import org.example.demo.locale.toDefaultMoneyString
import org.example.demo.productos.complementos.services.ComplementoService
import org.example.demo.productos.models.Bebida
import org.example.demo.productos.models.Comida
import org.example.demo.productos.models.Complemento
import org.example.demo.routes.RoutesManager
import java.io.File
import java.lang.Exception

class SeleccionarComplViewModel(
    private val servicio : ComplementoService
) {

    val state: SimpleObjectProperty<ComplementoState> = SimpleObjectProperty(ComplementoState())

    init {
        servicio.import(File("data", "complemento.csv")).onSuccess {
            initState(it)
        }
    }
    fun updateState(complemento: Complemento){
        state.value = state.value.copy(
            tipo = when(complemento){
                is Bebida-> "Bebida"
                is Comida-> "Comida"
                else -> "No detectado"
            },
            nombre = complemento.id,
            precio = when(complemento) {
                is Bebida -> complemento.precio.toDefaultMoneyString()
                is Comida -> complemento.precio.toDefaultMoneyString()
                else -> "No detectado"
            },
            imagen = when(complemento.id){
                "PALOMITAS"-> Image(RoutesManager.getResourceAsStream("images/palomitas.png"))
                "FRUTOSSECOS"->Image(RoutesManager.getResourceAsStream("images/frutossecos.png"))
                "PATATAS"->Image(RoutesManager.getResourceAsStream("images/patatas.png"))
                "AGUA"->Image(RoutesManager.getResourceAsStream("images/agua.png"))
                "REFRESCO"->Image(RoutesManager.getResourceAsStream("images/refresco.png"))
                else -> Image(RoutesManager.getResourceAsStream("images/interrogacion.png"))
            }

        )
    }

    fun filtratComplementos(precio: String,nombre: String,tipo: String):List<Complemento>{

        return state.value.complementos
            .asSequence()
            .filter {
                it.id == nombre
            }.filter {
                (it is Comida && tipo=="Comida")
            }.filter {
                (it is Bebida && tipo=="Bebida")
            }.filter {
                (it as Bebida).precio.toDefaultMoneyString() == precio
            }.filter {
                (it as Comida).precio.toDefaultMoneyString() == precio
            }
            .toList()
    }


    fun borrarSeleccionado(){
        val lista= mutableListOf<Complemento>()
        state.value = state.value.copy(
            complementosSeleccionados = lista
        )
    }

    private fun initState(lista: List<Complemento>) {
        val listaTipos= mutableListOf<String>("All")
        lista.forEach {
            if (it is Bebida) {
                listaTipos.add("Bebida")
            }
            else if ( it is Comida) {
                listaTipos.add("Comida")
            }
        }
        val listaPrecio = mutableListOf("All")
        lista.forEach {
            when(it){
                is Bebida-> listaPrecio.add(it.precio.toDefaultMoneyString())
                is Comida-> listaPrecio.add(it.precio.toDefaultMoneyString())
            }
        }

        state.value = state.value.copy(
            complementos = lista,
            tipos = listaTipos.distinct().sorted(),
            precios = listaPrecio.distinct().sorted()
        )
    }

    data class ComplementoState(
        val tipo:String="" ,
        val nombre:String="",
        val precio:String="",
        val cantidad:Int=0,
        val imagen:Image = Image(RoutesManager.getResourceAsStream("images/interrogacion.png")),
        val complementos:List<Complemento> = listOf(),
        val tipos:List<String> = listOf(),
        val precios:List<String> = listOf(),
        val complementosSeleccionados : MutableList<Complemento> = mutableListOf()
    )
}