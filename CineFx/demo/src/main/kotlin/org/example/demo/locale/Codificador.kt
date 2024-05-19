package org.example.demo.locale

import kotlin.io.encoding.Base64

fun String.encodeToBase64(): String{
    val bytes = this.toByteArray()
    return String(java.util.Base64.getEncoder().encode(bytes))
}