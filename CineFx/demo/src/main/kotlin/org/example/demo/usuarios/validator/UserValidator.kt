package org.example.demo.usuarios.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.demo.usuarios.errors.UserError
import org.example.demo.usuarios.models7.Usuario
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * Realizamos validaciones sobre las propiedades de usuario.
 * @return Devuelve si todas las validaciones son correctas devuelve un objeto ok.
 * @author Raúl Fernández, Yahya El Hadri, Samuel Cortés, Javier Hernández
 * @since 1.0
 */

fun Usuario.validate(): Result<Usuario, UserError>{
    if (this.nombre.isEmpty() || this.nombre.isBlank()){
        return Err(UserError.ValidateProblem("El nombre no puede estar vacío"))
    }
    if (this.nombre.isEmpty() || this.nombre.isBlank()){
        return Err(UserError.ValidateProblem("El nombre no puede estar vacío"))
    }
    if (this.apellidos.isEmpty() || this.apellidos.isBlank()){
        return Err(UserError.ValidateProblem("Los apellidos no pueden estar vacios"))
    }
    val regexMail = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,3}$")
    if(!this.email.matches(regexMail)){
        return Err(UserError.ValidateProblem("El correo electrónico no es correcto"))
    }
    val regexContraseña = Regex("^(?=.[a-z])(?=.[A-Z])(?=.\\d)(?=.[@\$!%?&])[A-Za-z\\d@\$!%?&]{12,}$")
    if(!this.contraseña.matches(regexContraseña) || this.contraseña.isEmpty() || this.contraseña.isBlank()){
        return Err(UserError.ValidateProblem("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial"))
    }
    return Ok(this)
}

fun validateNombre(nombre: String): Boolean{
    if(nombre.isEmpty() || nombre.isBlank()){
        return false
    }
    return true
}

/**
 * Valida los apellidos de un usuario.
 * @return Devuelve un error si el apellido esta vacio y el apellido validado si es válido.
 * @author Raúl Fernández, Yahya El Hadri, Samuel Cortés, Javier Hernández
 * @since 1.0
 */

 fun validateApellidos(apellidos: String): Boolean{
    if (apellidos.isEmpty() || apellidos.isBlank()){
        return false
    }
    return true
}

/**
 * Valida la dirección de correo electrónico de un usuario.
 * @return Devuelve un error si no sigue el formato esperado y la dirección de correo electrónico validada si es válida.
 * @author Raúl Fernández, Yahya El Hadri, Samuel Cortés, Javier Hernández
 * @since 1.0
 */

fun validateEmail(email: String): Boolean{
    val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    if(!email.matches(regex)){
        return false
    }
    return true
}

/**
 * Valida la contraseña de un usuario utilizando una expresión regular.
 * @return Devuelve un error si no sigue el formato esperado, y la contraseña validada si es válida.
 * @author Raúl Fernández, Yahya El Hadri, Samuel Cortés, Javier Hernández
 * @since 1.0
 */

fun validateContraseña(contraseña: String): Boolean{
    val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{12,}$")
    if(!contraseña.matches(regex)){
        return false
    }
    return true
}
