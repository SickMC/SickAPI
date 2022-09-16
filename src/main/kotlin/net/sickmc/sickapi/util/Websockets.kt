package net.sickmc.sickapi.util

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.websocket.*
import io.ktor.http.*
import io.ktor.websocket.*
import kotlinx.serialization.Serializable

val webClient = HttpClient(CIO) {
    install(WebSockets)
}

suspend fun send(channel: String, frame: Frame) = webSocketClient(channel) {
    send(frame)
}

suspend fun sendSerialized(channel: String, text: @Serializable NetworkMessage) =
    webSocketClient(channel) { sendSerialized(text) }

suspend inline fun webSocketClient(
    channel: String, crossinline block: suspend DefaultClientWebSocketSession.() -> Unit
) {
    webClient.webSocket(
        method = HttpMethod.Get,
        host = env("INTERNAL_SERVER_HOST"),
        port = env("INTERNAL_SERVER_PORT").toInt(),
        path = "/$channel"
    ) { block.invoke(this) }
}