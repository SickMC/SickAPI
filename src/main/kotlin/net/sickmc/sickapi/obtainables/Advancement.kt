package net.sickmc.sickapi.obtainables

import kotlinx.serialization.Serializable
import net.sickmc.sickapi.util.ColorHolder
import net.sickmc.sickapi.util.UUIDSerializer
import java.util.*

@Serializable
data class Advancement(
    val uuid: @Serializable(with = UUIDSerializer::class) UUID,
    val name: String,
    val color: ColorHolder,
    val maxLevels: Int
)

@Serializable
data class PlayerAdvancement(val advancement: Advancement, var currentLevel: Int)