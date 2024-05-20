package org.example.demo.view.viewModel

import com.github.michaelbull.result.onSuccess
import javafx.beans.property.SimpleObjectProperty
import org.example.demo.locale.encodeToBase64
import org.example.demo.usuarios.services.UserService

class LoginViewModel(
    private val service: UserService
) {

    val state: SimpleObjectProperty<LoginState> = SimpleObjectProperty(LoginState())

    fun validarAdmin(email:String, contraseña: String):Boolean{
        if (email == "adminJYRS@JYRS.es" && contraseña.encodeToBase64() == "SnlScyowNTAyMDMwMw==" ) return true

        return false
    }
    fun validarUsuario(email: String):Boolean{
         service.findByEmail(email).onSuccess {

             return true
         }
        println("false")
        return false
    }

    fun validarContraseña(email:String, contraseña: String):Boolean{
        return service.findByEmail(email).value.contraseña == contraseña.encodeToBase64()
    }
    data class LoginState(
        var usuario:String=""
    )
}