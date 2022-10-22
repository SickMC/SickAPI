package net.sickmc.sickapi

import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import net.sickmc.sickapi.obtainables.PlayerAdvancement
import net.sickmc.sickapi.obtainables.PlayerGadget
import net.sickmc.sickapi.rank.Rank
import net.sickmc.sickapi.rank.rankCache
import net.sickmc.sickapi.util.*
import java.util.*

@Serializable
data class SickPlayer(
    val uuid: @Serializable(with = UUIDSerializer::class) UUID,
    val uuidString: String,
    var permanentRank: Rank,
    var currentRank: Rank,
    var rankExpire: Long?,
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
    abstract val uuidString: String
    abstract val gameType: GameType
}

val playerCache = PlayerCache()

class PlayerCache : Cache<UUID, SickPlayer>() {
    suspend fun create(key: UUID): SickPlayer {
        val new = SickPlayer(
            key,
            key.toString(),
            rankCache.get("default") ?: error("default rank in playerCache not found!"),
            rankCache.get("default") ?: error("default rank in playerCache not found!"),
            null,
            0,
            0,
            0,
            0,
            mutableListOf(),
            mutableListOf(),
            null,
            mutableListOf(),
            0,
            mutableListOf()
        )
        databaseScope.launch {
            players.insertOne(new)
        }
        return new
    }
}