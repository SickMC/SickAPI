package net.sickmc.sickapi.util

import kotlinx.serialization.Serializable
import java.awt.Color

@Serializable
open class ColorHolder(val fallbackColor: Int)

@Serializable
data class Gradient(val startColor: Int, val endColor: Int) : ColorHolder(startColor)

@Serializable
data class StaticColor(val color: Int) : ColorHolder(color)

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


object Colors {
    val black = 0x000000
    val white = 0xFFFFFF
    val gray = 0x585557
    val red = 0xC40B0D
    val darkRed = 0x5A0607
    val green = 0x00DD04
    val darkGreen = 0x005703
    val yellow = 0xEFDC08
    val darkYellow = 0x9C9005
    val lightBlue = 0x66B7FF
    val blue = 0x5D77FF
    val darkBlue = 0x2E3BFF
    val pink = 0xFF58DF
    val purple = 0x751B6B
    val brown = 0x693400
    val gold = 0xD09109
}