package arrtglorin.kubernetes.kesource

import arrtglorin.kubernetes.kesource.pages.RootPage
import io.kvision.state.ObservableValue
import kotlin.reflect.KClass

val currentLocation = ObservableValue(RootPage.segment)

interface Entity<T : Any> {
    val id: T
}


interface StorageService {

    fun <T : Any> get(id: T, type: KClass<Entity<T>>): Entity<T>?
}
