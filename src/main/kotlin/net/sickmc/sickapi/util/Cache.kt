package net.sickmc.sickapi.util

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class Cache<T, V>(private val loader: suspend T.() -> V?, private val creator: suspend T.() -> V) {

    val mutex: Mutex = Mutex()
    val cache: HashMap<T, V> = hashMapOf()

    suspend fun get(key: T, reload: Boolean = false): V? {
        if (reload) {
            val new = loader.invoke(key) ?: return null
            cache[key] = new
            return new
        }
        mutex.withLock {
            return cache[key] ?: kotlin.run {
                val new = loader.invoke(key) ?: return null
                cache[key] = new
                return new
            }
        }
    }

    suspend fun create(key: T): V {
        val created = creator(key)
        cache[key] = created
        return created
    }
}

inline fun <reified T, reified V> cache(crossinline builder: CacheBuilder<T, V>.() -> Unit): Cache<T, V> {
    return CacheBuilder<T, V>().apply(builder).build()
}

class CacheBuilder<T, V> {

    var loader: (suspend T.() -> V?)? = null
    var creator: (suspend T.() -> V)? = null

    fun loadItem(loader: suspend T.() -> V?) {
        this.loader = loader
    }

    fun createItem(creator: suspend T.() -> V) {
        this.creator = creator
    }

    fun build(): Cache<T, V> {
        if (loader == null || creator == null) error("Loader or creator in cache was null!")
        return Cache(loader!!, creator!!)
    }

}