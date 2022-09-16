package net.sickmc.sickapi.util

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.model.Filters
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable
import net.sickmc.sickapi.SickPlayer
import net.sickmc.sickapi.rank.RankGroup
import org.litote.kmongo.coroutine.CoroutineCollection
import org.litote.kmongo.coroutine.CoroutineDatabase
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo

val databaseScope = CoroutineScope(Dispatchers.Default + SupervisorJob())

private val connectionString = ConnectionString(
    "mongodb://${env("MONGO_USERNAME")}:${env("MONGO_PASSWORD")}@${
        env(
            "MONGO_ADDRESS"
        )
    }:${env("MONGO_PORT")}/?authSource=${env("MONGO_DATABASE")}"
)

val mongoClient =
    KMongo.createClient(MongoClientSettings.builder().applyConnectionString(connectionString).build()).coroutine

val db = mongoClient.getDatabase(env("MONGO_DATABASE"))

var rankGroups: CoroutineCollection<RankGroup> = db.getCollection("rankGroups")
var players: CoroutineCollection<SickPlayer> = db.getCollection("sickPlayers")

suspend fun initMongo() {
    rankGroups = db.getAndCreateCollection("rankGroups")
    players = db.getAndCreateCollection("sickPlayers")
}

suspend fun <T : @Serializable Any> CoroutineCollection<T>.retrieveOne(key: String, value: String): T? {
    return this.find(Filters.eq(key, value)).first()
}

fun <T : @Serializable Any> CoroutineCollection<T>.retrieveMany(key: String, value: String): Flow<T> {
    return this.find(Filters.eq(key, value)).toFlow()
}

suspend fun <T : @Serializable Any> CoroutineCollection<T>.replace(key: String, value: String, doc: T) {
    this.replaceOne(Filters.eq(key, value), doc)
}

suspend inline fun <reified T : @Serializable Any> CoroutineDatabase.getAndCreateCollection(name: String): CoroutineCollection<T> {
    return if (this.listCollectionNames().contains(name)) this.getCollection(name)
    else {
        this.createCollection(name)
        this.getCollection(name)
    }
}