package net.sickmc.sickapi.rank

import kotlinx.serialization.Serializable
import net.sickmc.sickapi.util.UUIDSerializer
import java.util.*

@Serializable
data class Rank(
    val uuid: @Serializable(with = UUIDSerializer::class) UUID,
    val name: String,
    val extraPermissions: MutableList<String>
)

val Rank.parent: RankGroup
    get() = rankGroupCache.cache.filterValues { it.ranks.contains(this) }.map { it.value }[0]