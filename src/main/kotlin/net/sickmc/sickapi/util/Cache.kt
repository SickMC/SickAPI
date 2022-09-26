package net.sickmc.sickapi.util

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

open class Cache<T, V> {

    val mutex: Mutex = Mutex()
    val cache: HashMap<T, V> = hashMapOf()

    suspend fun get(key: T): V? {
        mutex.withLock {
            return cache[key]
        }
    }

    suspend fun set(key: T, value: V) {
        mutex.withLock {
            cache[key] = value
        }
    }
}

inline fun <reified T, reified V> cache(): Cache<T, V> = Cache()