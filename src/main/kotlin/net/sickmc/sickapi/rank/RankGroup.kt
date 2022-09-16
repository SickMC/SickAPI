package net.sickmc.sickapi.rank

import kotlinx.serialization.Serializable
import net.sickmc.sickapi.util.UUIDSerializer
import java.util.*

@Serializable
data class RankGroup(
    val uuid: @Serializable(with = UUIDSerializer::class) UUID,
    val name: String,
    val color: Int,
    val ranks: MutableList<Rank>,
    val permissions: MutableList<String>
)