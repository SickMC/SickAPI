package net.sickmc.sickapi.obtainables

import kotlinx.serialization.Serializable
import net.sickmc.sickapi.SickPlayer
import net.sickmc.sickapi.util.ColorHolder

@Serializable
abstract class Reward {
    abstract val name: String
    abstract val color: ColorHolder

    abstract suspend fun applyTo(player: SickPlayer)
}