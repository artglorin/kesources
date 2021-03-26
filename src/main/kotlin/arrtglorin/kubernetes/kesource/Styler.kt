@file:Suppress("UnsafeCastFromDynamic")

package arrtglorin.kubernetes.kesource

import io.kvision.core.Container


val designData = io.kvision.require("design/design.json")

fun Container.design(designName: String) {
    console.log("apply design = %", designName)
    setAttribute("design", (getAttribute("design")?.plus(", ")?:"") + designName)
    val styles = designName
        .split('.')
        .fold(designData) { acc, name -> if (acc == null) null else acc[name] }
    if (styles == null) {
        console.error("Cannot load design by name '$designName'.", designData)
    } else {
        for(i in styles ){
            addCssClass(i)
        }
    }
}
