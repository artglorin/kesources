package arrtglorin.kubernetes.kesource.bottstrap

import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.listGroup
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.listGroupFlush
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.listGroupItem
import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.listGroupItemAction
import io.kvision.core.Container
import io.kvision.html.*


fun Container.bsList(f: ListTag.() -> Unit) {
    listTag( type = ListType.UNSTYLED, classes = Bootstrap { +listGroup }) {
        f(this)
    }
}

fun Container.bsListFlush(f: ListTag.() -> Unit) {
    listTag(type = ListType.UNSTYLED, classes = Bootstrap { +listGroupFlush }) {
        f(this)
    }
}

fun Container.bsDivListFlush(f: Div.() -> Unit) {
    div(classes = Bootstrap{+listGroupFlush})
        .apply(f)
}

fun Container.bsListGroupActionLink(label: String, f: Link.() -> Unit) {
    link(label = label, classes = Bootstrap{+listGroupItem; +listGroupItemAction})
        .apply(f)
}

fun Container.bsListItem(f: Li.() -> Unit) {
    li(classes = Bootstrap { +listGroupItem }) {
        f(this)
    }
}
