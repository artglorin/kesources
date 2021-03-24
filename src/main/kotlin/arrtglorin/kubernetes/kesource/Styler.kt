package arrtglorin.kubernetes.kesource

import io.kvision.core.Container


val designData = io.kvision.require("design/design.json")

fun Container.design(designName: String) {
    println("DESIGN $designName")
    console.log("data", designData)
    setAttribute("design", (getAttribute("design")?.plus(", ")?:"") + designName)
    val classes = designName.split('.')
    val styles = classes.fold(designData) { acc, name -> if (acc == null) null else acc[name] }
    if (styles == null) {
        console.error("Cannot load design by name '$designName'.", designData)
    } else {
        for(i in styles ){
            addCssClass(i)
        }
    }
}
