package net.sickmc.sickapi.util

import kotlinx.serialization.Serializable
import java.awt.Color

@Serializable
open class ColorHolder

@Serializable
data class Gradient(val startColor: Int, val endColor: Int) : ColorHolder()

@Serializable
data class StaticColor(val color: Int) : ColorHolder()

fun Gradient.getColor(percent: Double): Int {
    val color1 = Color(startColor)
    val color2 = Color(endColor)
    val inversePercent: Double = 1.0 - percent
    val color = Color(
        (color1.red * percent + color2.red * inversePercent).toInt(),
        (color1.green * percent + color2.green * inversePercent).toInt(),
        (color1.blue * percent + color2.blue * inversePercent).toInt()
    )
    return color.red * 65536 + color.green * 256 + color.blue
}