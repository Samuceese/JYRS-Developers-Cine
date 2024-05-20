package org.example.demo.view.viewModel

import com.github.michaelbull.result.onSuccess
import org.example.demo.usuarios.models.Cliente
import org.example.demo.usuarios.services.UserService
import org.example.demo.usuarios.validator.validateApellidos
import org.example.demo.usuarios.validator.validateContraseña
import org.example.demo.usuarios.validator.validateEmail
import org.example.demo.usuarios.validator.validateNombre

class RegistroViewModel(
    private val service: UserService
) {
    fun darAltaCliente(nombre:String,apellidos:String, email:String, contraseña:String){
        //service.save(Cliente(nombre=nombre, apellidos = apellidos, email = email, contraseña = contraseña))
    }
    fun comprobarUsuario(email: String):Boolean{
        service.findByEmail(email).onSuccess {
            return false
        }
        return true
    }
    fun comprobarEmail(email: String):Boolean{
        return validateEmail(email)
    }
    fun comprobarContraseña(contraseña: String):Boolean{
        return validateContraseña(contraseña)
    }
    fun comprobarApellido(apellidos: String):Boolean{
        return validateApellidos(apellidos)
    }
    fun comprobarNombre(nombre: String):Boolean{
        return validateNombre(nombre)
    }
}