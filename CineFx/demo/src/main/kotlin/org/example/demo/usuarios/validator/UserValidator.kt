package org.example.demo.usuarios.validator

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import org.example.demo.usuarios.errors.UserError
import org.example.demo.usuarios.models.Usuario

fun Usuario.validate(): Result<Usuario, UserError>{
    validateNombre(this)
    validateApellidos(this)
    validateEmail(this)
    validateContraseña(this)
    return Ok(this)
}

fun validateNombre(usuario: Usuario): Result<String, UserError>{
    if (usuario.nombre.isEmpty() || usuario.nombre.isBlank()){
        return Err(UserError.ValidateProblem("El nombre no puede estar vacío"))
    }
    return Ok(usuario.nombre)
}

fun validateApellidos(usuario: Usuario): Result<String, UserError>{
    if (usuario.apellidos.isEmpty() || usuario.apellidos.isBlank()){
        return Err(UserError.ValidateProblem("Los apellidos no pueden estar vacios"))
    }
    return Ok(usuario.apellidos)
}

fun validateEmail(usuario: Usuario): Result<String, UserError>{
    val regex = Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    if(!usuario.email.matches(regex)){
        return Err(UserError.ValidateProblem("El correo electrónico no es correcto"))
    }
    return Ok(usuario.email)
}

fun validateContraseña(usuario: Usuario): Result<String, UserError>{
    val regex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{12,}$")
    if(!usuario.contraseña.matches(regex)){
        return Err(UserError.ValidateProblem("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial"))
    }
    return Ok(usuario.contraseña)
}