package net.sickmc.sickapi.punishment

import kotlinx.serialization.Serializable

@Serializable
enum class PunishmentReason {
    Cheating, Bug_Abusing, Appearance, Conduct
}