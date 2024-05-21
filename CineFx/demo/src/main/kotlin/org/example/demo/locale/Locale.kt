package org.example.demo.locale

import java.text.NumberFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

fun LocalDate.toDefaultDateTimeString():String{
    return DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault()).format(this)
}
fun Double.toDefaultMoneyString():String{
    return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)
}