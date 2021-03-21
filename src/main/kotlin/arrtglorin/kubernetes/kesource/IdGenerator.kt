package arrtglorin.kubernetes.kesource

object IdGenerator {
    private var currentId = 0

    fun nextId() = currentId++

    fun nextIdString() = "${nextId()}"
}
