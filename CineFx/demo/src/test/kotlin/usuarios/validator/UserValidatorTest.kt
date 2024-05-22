package usuarios.validator

import org.example.demo.usuarios.errors.UserError
import org.example.demo.usuarios.models.Cliente
import org.example.demo.usuarios.validator.validate
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserValidatorTest {

    @Test
    fun validarAllCorrecto() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyowNTAyMDMwMw==", "adminJYRS@JYRS.es")
        val result = user.validate().value
        assertEquals("Juan", result.nombre)
        assertEquals("adminJYRS@JYRS.es", result.email)
    }

    @Test
    @DisplayName("Validación del nombre del usuario debería devolver un error de validación")
    fun validarNombre() {
        val user = Cliente(2,"", "Perez", "SnlScyowNTAyMDMwMw==", "adminJYRS@JYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "El nombre no puede estar vacío")
    }

    @Test
    @DisplayName("Validación del apellido del usuario debería devolver un error de validación")
    fun validarApellido() {
        val user = Cliente(2,"Juan", "", "SnlScyowNTAyMDMwMw==", "adminJYRS@JYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "Los apellidos no pueden estar vacios")
    }

    @Test
    @DisplayName("Validación del email que no contenga arroba debería devolver error de validación")
    fun validarEmailNoContieneArroba() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyowNTAyMDMwMw==", "adminJYRSJYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "El correo electrónico no es correcto")
    }

    @Test
    @DisplayName("Validación del email que esté vacío debería devolver error de validación")
    fun validarEmailVacio() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyowNTAyMDMwMw==", "")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "El correo electrónico no es correcto")
    }

    @Test
    @DisplayName("Validación del email que no contenga punto debería devolver error de validación")
    fun validarEmailNoContienePunto() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyowNTAyMDMwMw==", "adminJYRS@JYRSes")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "El correo electrónico no es correcto")
    }

    @Test
    @DisplayName("Validación del email que no contiene más de dos letras después del punto debería devolver error de validación")
    fun validarEmailNoSuperiorADosLetras() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyowNTAyMDMwMw==", "adminJYRS@JYRS.e")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "El correo electrónico no es correcto")
    }

    @Test
    @DisplayName("Validación del email que contiene más de tres letras después del punto debería devolver error de validación")
    fun validarEmailSuperiorATresLetras() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyowNTAyMDMwMw==", "adminJYRS@JYRS.come")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "El correo electrónico no es correcto")
    }

    @Test
    @DisplayName("Validación de la contraseña vacía debería devolver error de validación")
    fun validarContrasenaVacia() {
        val user = Cliente(2,"Juan", "Perez", "", "adminJYRS@JYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial")
    }

    @Test
    @DisplayName("Validación de la contraseña sin una mayúscula debería devolver error de validación")
    fun validarContrasenaSinMayuscula() {
        val user = Cliente(2,"Juan", "Perez", "snlscyowntaymdmwmw==", "adminJYRS@JYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial")
    }

    @Test
    @DisplayName("Validación de la contraseña sin una minúscula debería devolver error de validación")
    fun validarContrasenaSinMinuscula() {
        val user = Cliente(2,"Juan", "Perez", "SNLSCYOWNTAYMDMWMW==", "adminJYRS@JYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial")
    }

    @Test
    @DisplayName("Validación de la contraseña sin carácter debería devolver error de validación")
    fun validarContrasenaSinCaracter() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyo5wNTty", "adminJYRS@JYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial")
    }

    @Test
    @DisplayName("Validación de la contraseña menor de 12 caracteres debería devolver error de validación")
    fun validarContrasenaMenor12() {
        val user = Cliente(2,"Juan", "Perez", "SnlS7ow==", "adminJYRS@JYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial")
    }

    @Test
    @DisplayName("Validación de la contraseña mayor de 12 caracteres debería devolver error de validación")
    fun validarContrasenaMayor12() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyowuduUGDHQU9iobfqh9==", "adminJYRS@JYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial")
    }

    @Test
    @DisplayName("Validación de la contraseña sin número")
    fun validarContrasenaSinNumero() {
        val user = Cliente(2,"Juan", "Perez", "SnlScyowugt==", "adminJYRS@JYRS.es")
        val result = user.validate().error
        assertTrue(result is UserError.ValidateProblem)
        assertEquals(result.message, "La contraseña no es válida, debe tener 12 carácteres, contener al menos una mayúscula y una minúsucla, un número y un caracter especial")
    }
}