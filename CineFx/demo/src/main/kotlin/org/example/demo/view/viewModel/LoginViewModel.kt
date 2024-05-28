package org.example.demo.view.viewModel

import com.github.michaelbull.result.mapBoth
import javafx.beans.property.SimpleObjectProperty
import org.example.demo.database.SqlDelightManager
import org.example.demo.locale.encodeToBase64
import org.example.demo.usuarios.models7.Usuario
import org.example.demo.usuarios.services.UserService

class LoginViewModel(
    private val service: UserService,
    private val dbManager: SqlDelightManager
) {
    init {
        dbManager.databaseQueries.insertUser(
            email = "a@gmail.com",
            nombre = "a",
            apellidos = "a",
            contrasena = "Hola123456789@".encodeToBase64(),
            tipo = "cliente",
            id = 12
        )
    }
    val state: SimpleObjectProperty<LoginState> = SimpleObjectProperty(LoginState())

    fun validarAdmin(email:String, contraseña: String):Boolean{
        if (email == "SomosJYRS@gmail.com" && contraseña.encodeToBase64() == "SnlScyowNTAyMDMwMw==" ) return true

        return false
    }
    fun validarUsuario(email: String):Boolean{
         service.findByEmail(email).mapBoth(
             success = {
                 return true
             },
             failure = {
                 println("No encontrado $email")
                 return false
             }
         )
    }

    fun validarContraseña(email:String, contraseña: String):Boolean{
        return service.findByEmail(email).value.contraseña == contraseña.encodeToBase64()
    }

    fun establecerUsuario(email: String) {
        state.value = state.value.copy(
            usuario = service.findByEmail(email).value
        )
    }

    data class LoginState(
        var usuario:Usuario?=null
    )
}