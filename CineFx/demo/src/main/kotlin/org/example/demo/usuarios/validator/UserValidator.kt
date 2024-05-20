package org.example.demo.usuarios.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.demo.usuarios.errors.UserError
import org.example.demo.usuarios.models.Usuario
import kotlin.jvm.Throws

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
    val regexMail = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    if(!this.email.matches(regexMail)){
        return Err(UserError.ValidateProblem("El correo electrónico no es correcto"))
    }
    val regexContraseña = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{12,}$")
    if(!this.contraseña.matches(regexContraseña)){
        return Err(UserError.ValidateProblem("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial"))
    }
    return Ok(this)
}
