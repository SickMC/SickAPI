package net.sickmc.sickapi.obtainables

import kotlinx.serialization.Serializable
import net.sickmc.sickapi.util.ColorHolder
import net.sickmc.sickapi.util.UUIDSerializer
import java.util.*

@Serializable
data class Gadget(
    val uuid: @Serializable(with = UUIDSerializer::class) UUID,
    val name: String,
    val color: ColorHolder,
    val levels: Int
)

@Serializable
data class PlayerGadget(val gadget: Gadget, val currentLevel: Int)