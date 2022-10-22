package net.sickmc.sickapi.obtainables

import kotlinx.serialization.Serializable
import net.sickmc.sickapi.util.ColorHolder
import net.sickmc.sickapi.util.StaticColor
import net.sickmc.sickapi.util.UUIDSerializer
import java.util.*

@Serializable
data class Gadget(
    val uuid: @Serializable(with = UUIDSerializer::class) UUID,
    val uuidString: String,
    val name: String,
    val description: String,
    val color: ColorHolder,
    val levels: Int
)

@Serializable
data class PlayerGadget(val gadget: Gadget, var currentLevel: Int)

val Gadget.Companion.gold
    get() = Gadget(
        UUID.fromString("d2c75c9a-d880-4bd6-9237-8d60ca134139"),
        "d2c75c9a-d880-4bd6-9237-8d60ca134139",
        "gold",
        "Given as a reward when you reach gold on the SickMC Discord",
        StaticColor(0xAA921E),
        1
    )

val Gadget.Companion.emerald
    get() = Gadget(
        UUID.fromString("d7376767-6f5b-4274-9a43-6094f852fca5"),
        "d7376767-6f5b-4274-9a43-6094f852fca5",
        "emerald",
        "Given as a reward when you reach emerald on the SickMC Discord",
        StaticColor(0x127B0D),
        1
    )