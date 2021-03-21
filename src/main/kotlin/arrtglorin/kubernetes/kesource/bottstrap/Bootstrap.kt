package arrtglorin.kubernetes.kesource.bottstrap

import io.kvision.core.Container

class BootstrapBuilder {
    val classes = mutableSetOf<String>()

    fun container(f: BsContainerBuilder.() -> Unit) {
        classes += BsContainerBuilder().apply(f).build()
    }

    fun row(f: BsRowBuilder.() -> Unit) {
        classes += BsRowBuilder().apply(f).build()
    }
}

class BsRowBuilder {
    val classes = mutableSetOf<String>()

    fun build(): Set<String> {

    }

    fun gap(f: BsGapBuilder.() -> Unit) {
        classes += BsGapBuilder().apply(f).build()
    }

}

class BsGapBuilder {
    fun build()
}

class BsContainerBuilder {
    var fluid = false
    fun build(): String =
        if (fluid) {
            "container-fluid"
        } else "container"
}

fun Container.bs(f: BootstrapBuilder.() -> Unit) {
    BootstrapBuilder().apply {
        classes
            .forEach {
                addCssClass(it)
            }
    }
}
