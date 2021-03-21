package arrtglorin.kubernetes.kesource.bottstrap

import arrtglorin.kubernetes.kesource.bottstrap.Bootstrap.Alert.bsCssAlertLight
import io.kvision.core.Container
import io.kvision.html.div

fun Container.bsInfo(text: String) {
    div {
        bsCssAlertLight()
        +text
    }
}
