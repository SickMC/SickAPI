package net.sickmc.sickapi

import kotlinx.serialization.Serializable
import net.sickmc.sickapi.obtainables.PlayerAdvancement
import net.sickmc.sickapi.obtainables.PlayerGadget
import net.sickmc.sickapi.rank.Rank
import net.sickmc.sickapi.util.UUIDSerializer
import java.util.*

@Serializable
data class SickPlayer(
    val uuid: @Serializable(with = UUIDSerializer::class) UUID,
    var permanentRank: Rank,
    var currentRank: Rank,
    var rankExpire: Long,
    var smucks: Int,
    var totalAddiction: Int,
    var addictionProgress: Int,
    var addictionLevel: Int,
    val gadgets: MutableList<PlayerGadget>,
    val advancements: MutableList<PlayerAdvancement>,
    var discordID: String?,
    val extraPermissions: MutableList<String>,
    var playtime: Long,
    var gamePlayers: MutableList<GamePlayer>
)

@Serializable
abstract class GamePlayer {
    abstract val uuid: @Serializable(with = UUIDSerializer::class) UUID
    abstract val gameType: GameType
}