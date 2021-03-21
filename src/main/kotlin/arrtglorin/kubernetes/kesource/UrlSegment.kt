package arrtglorin.kubernetes.kesource

import io.kvision.routing.routing
import kotlinx.browser.window

data class UrlSegment(
    val parent: UrlSegment? = null,
    val segment: String
) {
    val path: String by lazy {
        parent
            ?.path
            ?.plus("/")
            ?.plus(segment)
            ?: segment
    }

    fun sibling(segment: String): UrlSegment =
        UrlSegment(parent, segment)

    fun child(segment: String): UrlSegment =
        UrlSegment(this, segment)

}

object Segments {
    val rootSegment = UrlSegment(segment = "#")
    val resourcesSegment = rootSegment.child("resources")
}
