package net.sickmc.sickapi.util

import kotlinx.coroutines.sync.Mutex

abstract class Cache<T, V> {

    protected abstract val mutex: Mutex
    protected abstract val cache: HashMap<T, V>

    abstract suspend fun get(key: T): V?
    abstract suspend fun set(key: T, value: V, create: Boolean = false): V
}