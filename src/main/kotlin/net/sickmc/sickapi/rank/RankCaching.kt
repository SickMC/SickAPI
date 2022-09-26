package net.sickmc.sickapi.rank

import net.sickmc.sickapi.util.cache
import net.sickmc.sickapi.util.rankGroups
import java.util.*

val rankCache = cache<String, Rank>()
val rankGroupCache = cache<UUID, RankGroup>()

suspend fun loadRanks() {
    rankGroups.find().toFlow().collect {
        rankGroupCache.set(it.uuid, it)
        it.ranks.forEach { rank ->
            rankCache.set(rank.name, rank)
        }
    }
}