package org.example.demo.locale

import java.text.NumberFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 *pasar local datetime a formato español
 * @author Yahya el hadri el bakkali
 * @since 1.0
 */
fun LocalDateTime.toDefaultDateTimeString():String{
    return DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(this)
}
/**
 *pasar double a moneda española
 * @author Yahya el hadri el bakkali
 * @since 1.0
 * @return x.x€
 */
fun Double.toDefaultMoneyString():String{
    return NumberFormat.getCurrencyInstance(Locale.getDefault()).format(this)
}