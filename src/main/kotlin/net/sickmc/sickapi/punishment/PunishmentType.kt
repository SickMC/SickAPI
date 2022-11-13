package net.sickmc.sickapi.punishment

import kotlinx.serialization.Serializable

@Serializable
enum class PunishmentType {
    Chat,
    Ban
}