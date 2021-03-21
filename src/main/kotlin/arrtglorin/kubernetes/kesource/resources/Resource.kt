package arrtglorin.kubernetes.kesource.resources

enum class ApiVersion(
    private val value: String
) {
    v1("v1");

    override fun toString(): String {
        return value;
    }
}


interface Resource {
    var id: Long?
    val apiVersion: ApiVersion
    val kind: String
    val metadata: Map<String, Any?>

    fun toJs():dynamic {
        val js = js("{}")
        id?.let {
            js.id = it
        }
        js.apiVersion =  apiVersion.name
        js.kind = kind
        js.metadata = js("{}")
        metadata
            .forEach { (key, value) ->
            js.metadata[key] = metaToJs(key, value)
        }
        return js
    }

    fun metaToJs(key: String, value: Any?): dynamic = value
}
