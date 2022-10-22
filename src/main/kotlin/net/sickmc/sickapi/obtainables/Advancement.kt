package net.sickmc.sickapi.obtainables

import kotlinx.serialization.Serializable
import net.sickmc.sickapi.util.ColorHolder
import net.sickmc.sickapi.util.StaticColor
import net.sickmc.sickapi.util.UUIDSerializer
import java.util.*

@Serializable
data class Advancement(
    val uuid: @Serializable(with = UUIDSerializer::class) UUID,
    val uuidString: String,
    val name: String,
    val description: String,
    val color: ColorHolder,
    val maxLevels: Int
)

@Serializable
data class PlayerAdvancement(val advancement: Advancement, var currentLevel: Int)

val Advancement.Companion.stoned: Advancement
    get() = Advancement(
        UUID.fromString("e8772604-ae70-4034-8fa9-616b73becee4"),
        "e8772604-ae70-4034-8fa9-616b73becee4",
        "stoned",
        "Given as a reward when you reach on the SickMC Discord stone.",
        StaticColor(0x828894),
        1
    )