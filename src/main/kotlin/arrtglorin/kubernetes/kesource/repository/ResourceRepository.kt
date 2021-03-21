package arrtglorin.kubernetes.kesource.repository

import arrtglorin.kubernetes.kesource.resources.Namespace
import arrtglorin.kubernetes.kesource.resources.toJs
import io.kvision.i18n.tr
import kotlinx.browser.window
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.w3c.dom.events.Event
import org.w3c.dom.events.EventListener

interface ResourceRepository {

    fun save(namespace: Namespace)
}

class IndexedDbRepository : ResourceRepository {
    private var db: IDBDatabase? = null
//    private lateinit var namespaceStorage: IDBObjectStore<Namespace>

    init {
        console.info("INIT IndexedDB")
        val wnd: dynamic = window
        wnd.indexedDB = wnd.indexedDB ?: wnd.mozIndexedDB ?: wnd.webkitIndexedDB ?: wnd
            .msIndexedDB ?: throw Exception("This browser don't support IndexedDB")
        wnd.IDBTransaction = wnd.IDBTransaction ?: wnd.webkitIDBTransaction ?: wnd.msIDBTransaction
        wnd.IDBKeyRange = wnd.IDBKeyRange ?: wnd.webkitIDBKeyRange ?: wnd.msIDBKeyRange
        (wnd.indexedDB.open("kesources") as IDBOpenDBRequest)
            .also { request ->
                val onError: (Event) -> Unit = {
                    window.alert("Cannot open indexed DB")
                    console.info("IndexedDB ERROR", it)

                }
                request.onerror = onError
                request.onsuccess = {
                    console.info("IndexedDB opened", it)
                    val target: dynamic = it.target
                    db = target.result as IDBDatabase
//                    namespaceStorage = db.createObjectStore(
//                        storeName = "namespaces",
//                        options = CreateStoreOptions(autoIncrement = true)
//                    )
                }
                request.onupgradeneeded = {
                    console.info("IndexedDB upgradeneeded", it)

                    val target: dynamic = it.target
                    db = target.result as IDBDatabase
                    db!!.onError = onError
                    val namespaceStorage = db!!.createObjectStore(
                        storeName = "namespaces",
                        options = js("""{ keyPath: "id", autoIncrement: true }""")
                    )
                    namespaceStorage.createIndex("name", "metadata.name")
                }
            }

    }

    override fun save(namespace: Namespace) {
        db?.transaction("namespaces", TransactionMode.readwrite, TransactioDurability.strict)
            ?.apply {
                onerror = {
                    console.error("Cannot save namespace", namespace)
                }

                val js = namespace.toJs()
                console.log("Save namespace ", js)
                objectStore("namespaces").add(js)
                commit()
            }
    }

}

enum class TransactionMode {
    readonly, readwrite, readwriteflush
}

enum class TransactioDurability {
    default, strict, relaxed
}

data class CreateStoreOptions(
    val keyPath: String? = null,
    val autoIncrement: Boolean = false
)

external interface IDBDatabase : EventTarget {

    val name: String
    val version: Long
    val objectStoreNames: List<String>
    var onError: (Event) -> Unit
    fun createObjectStore(storeName: String, options: dynamic ): IDBObjectStore

    fun close()
    fun createMutableFile()
    fun deleteObjectStore()
    fun transaction(
        storageName: String,
        type: TransactionMode = definedExternally,
        durability: TransactioDurability = definedExternally
    ): IDBTransaction
}

data class IDBIndexParameters(
    val unique: Boolean = true,
    val multiEntry: Boolean = false,
    val locale: String? = null
)

external interface IDBIndex
external interface IDBObjectStore {
    val indexNames: List<IDBIndex>
    val keyPath: String?
    val name: String
    val transaction: IDBTransaction?
    val autoIncrement: Boolean
    fun add(value: dynamic, key: String = definedExternally)
    fun clear()
    fun count()
    fun createIndex(
        indexName: String,
        keyPath: String? = definedExternally,
        objectParameters: IDBIndexParameters = definedExternally
    )

    fun createIndex(
        indexName: String,
        keyPath: List<String>? = definedExternally,
        objectParameters: IDBIndexParameters = definedExternally
    )

    fun delete()
    fun deleteIndex()
    fun get()
    fun getKey()
    fun getAll()
    fun getAllKeys()
    fun index()
    fun openCursor()
    fun openKeyCursor()
    fun put()
}


external interface IDBSource

external interface IDBIndexSource : IDBSource, IDBIndex
external interface IDBObjectStoreSource<T> : IDBSource, IDBObjectStore

external class DOMException {
    val message: String
    val name: String
}

external interface EventTarget {
    fun addEventListener(
        type: String,
        callback: EventListener?,
        options: dynamic = definedExternally
    )

    fun addEventListener(
        type: String,
        callback: ((Event) -> Unit)?,
        options: dynamic = definedExternally
    )

    fun removeEventListener(
        type: String,
        callback: EventListener?,
        options: dynamic = definedExternally
    )

    fun removeEventListener(
        type: String,
        callback: ((Event) -> Unit)?,
        options: dynamic = definedExternally
    )

    fun dispatchEvent(event: Event): Boolean

    var onerror: (Event) -> Unit
    var onsuccess: (Event) -> Unit
}

external enum class ReadyState {
    pending, done
}

external interface IDBTransaction : EventTarget {
    val db: IDBDatabase
    val durability: TransactioDurability
    val mode: TransactionMode
    val objectStoreNames: List<String>
    val error: DOMException?

    fun abort()
    fun objectStore(storeName: String): IDBObjectStore
    fun commit()
}

external interface IDBRequest<T> : EventTarget {
    val result: T?
    val error: DOMException?
    val source: IDBSource?
    val readyState: ReadyState
    val transaction: IDBTransaction?
}

external interface IDBOpenDBRequest : IDBRequest<IDBDatabase> {

    var onupgradeneeded: (Event) -> Unit

}
