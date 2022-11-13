package net.sickmc.sickapi.punishment

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable
import net.sickmc.sickapi.util.UUIDSerializer
import java.util.*

@Serializable
data class Punishment(
    val uuid: @Serializable(with = UUIDSerializer::class) UUID,
    val uuidString: String,
    val type: PunishmentType,
    val reason: PunishmentReason,
    val expirationDate: Instant,
    val moderator: @Serializable(with = UUIDSerializer::class) UUID
)
