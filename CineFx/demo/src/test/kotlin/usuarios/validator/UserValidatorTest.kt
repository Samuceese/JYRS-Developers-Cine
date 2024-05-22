package usuarios.validator

import org.example.demo.productos.butaca.errors.ButacaError
import org.example.demo.usuarios.errors.UserError
import org.example.demo.usuarios.models.Cliente
import org.example.demo.usuarios.validator.validateUser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserValidatorTest {

    @Test
    fun validarAllCorrecto() {
        val user = Cliente(2,"Juan", "Perez", "Abcdef1234$%", "adminJYRS@JYRS.es")
        val result = validateUser(user).isOk
        assertTrue(result)
    }


    @Test
    @DisplayName("Validación del nombre del usuario debería devolver un error de validación")
    fun validarNombre() {
        val user = Cliente(2,"", "Perez", "Abcdef1234$%", "adminJYRS@JYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals( "El nombre no puede estar vacío", result.message)
    }

    @Test
    @DisplayName("Validación del apellido del usuario debería devolver un error de validación")
    fun validarApellido() {
        val user = Cliente(2,"Juan", "", "Abcdef1234$%", "adminJYRS@JYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals( "Los apellidos no pueden estar vacios", result.message)
    }

    @Test
    @DisplayName("Validación del email que no contenga arroba debería devolver error de validación")
    fun validarEmailNoContieneArroba() {
        val user = Cliente(2,"Juan", "Perez", "Abcdef1234$%", "adminJYRSJYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals( "El correo electrónico no es correcto", result.message,)
    }

    @Test
    @DisplayName("Validación del email que esté vacío debería devolver error de validación")
    fun validarEmailVacio() {
        val user = Cliente(2,"Juan", "Perez", "Abcdef1234$%", "")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("El correo electrónico no es correcto", result.message)
    }

    @Test
    @DisplayName("Validación del email que no contenga punto debería devolver error de validación")
    fun validarEmailNoContienePunto() {
        val user = Cliente(2,"Juan", "Perez", "Abcdef1234$%", "adminJYRS@JYRSes")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("El correo electrónico no es correcto", result.message)
    }

    @Test
    @DisplayName("Validación del email que no contiene más de dos letras después del punto debería devolver error de validación")
    fun validarEmailNoSuperiorADosLetras() {
        val user = Cliente(2,"Juan", "Perez", "Abcdef1234$%", "adminJYRS@JYRS.e")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("El correo electrónico no es correcto", result.message)
    }

    @Test
    @DisplayName("Validación de email que contiene tres letras después del punto, deberia de devolver Ok")
    fun validarEmailTresLetrasDespuesPunto(){
        val user = Cliente(2,"Juan", "Perez", "Abcdef1234$%", "adminJYRS@JYRS.com")
        val result = validateUser(user).isOk
        assertTrue(result)
    }

    @Test
    @DisplayName("Validación del email que contiene más de tres letras después del punto debería devolver error de validación")
    fun validarEmailSuperiorATresLetras() {
        val user = Cliente(2,"Juan", "Perez", "Abcdef1234$%", "adminJYRS@JYRS.come")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("El correo electrónico no es correcto", result.message)
    }

    @Test
    @DisplayName("Validación de la contraseña vacía debería devolver error de validación")
    fun validarContrasenaVacia() {
        val user = Cliente(2,"Juan", "Perez", "", "adminJYRS@JYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial", result.message)
    }

    @Test
    @DisplayName("Validación de la contraseña sin una mayúscula debería devolver error de validación")
    fun validarContrasenaSinMayuscula() {
        val user = Cliente(2,"Juan", "Perez", "abcdef1234$%", "adminJYRS@JYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial", result.message)
    }

    @Test
    @DisplayName("Validación de la contraseña sin una minúscula debería devolver error de validación")
    fun validarContrasenaSinMinuscula() {
        val user = Cliente(2,"Juan", "Perez", "ABCDEF1234$%", "adminJYRS@JYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial", result.message)
    }

    @Test
    @DisplayName("Validación de la contraseña sin carácter debería devolver error de validación")
    fun validarContrasenaSinCaracter() {
        val user = Cliente(2,"Juan", "Perez", "Abcdef12343", "adminJYRS@JYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial", result.message)
    }

    @Test
    @DisplayName("Validación de la contraseña menor de 12 caracteres debería devolver error de validación")
    fun validarContrasenaMenor12() {
        val user = Cliente(2,"Juan", "Perez", "x2*3&&7", "adminJYRS@JYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial", result.message)
    }

    @Test
    @DisplayName("Validación de la contraseña mayor de 12 caracteres debería devolver error de validación")
    fun validarContrasenaMayor12() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyowuduUGDHQU9iobfqh9==", "adminJYRS@JYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial", result.message)
    }

    @Test
    @DisplayName("Validación de la contraseña sin número")
    fun validarContrasenaSinNumero() {
        val user = Cliente(2,"Juan", "Perez", "AbcdefeeeE$%", "adminJYRS@JYRS.es")
        val result = validateUser(user).error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals("La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial", result.message)
    }
}