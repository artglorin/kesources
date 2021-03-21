package arrtglorin.kubernetes.kesource.resources

class Namespace(
    name: String
) : Resource {
    override var id: Long? = null
    private val meta = mutableMapOf<String, Any>("name" to name)
    override val apiVersion = ApiVersion.v1
    override val kind = "Namespace"
    override val metadata: Map<String, Any> = meta

    fun addMeta(key: String, value: Any) {
        meta[key] = value
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Namespace) return false

        if (meta != other.meta) return false
        if (apiVersion != other.apiVersion) return false
        if (kind != other.kind) return false

        return true
    }

    override fun hashCode(): Int {
        throw Exception("Namespace is mutable structure. Do not use it in hash structures.");
    }

}


fun Any.toJs(): dynamic {
//    val js: dynamic = this
//    val fields: Map<String, Any?> = js.entries()
//    val result = js("{}")
//    for ((key, value) in fields) {
//        if (key.startsWith('_')) {
//            continue
//        }
//        result[key] = value?.toJs()
//    }
    return JSON.stringify(this)
}
