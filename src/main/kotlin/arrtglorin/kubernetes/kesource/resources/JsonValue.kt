package arrtglorin.kubernetes.kesource.resources

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@kotlin.Suppress("RemoveRedundantQualifierName")
@Serializable
data class JsonField<out T : JsonValue>(
    val name: String,
    val value: T
)

@Serializable
sealed class JsonValue() {

    @Serializable
    object JsonNullValue : JsonValue() {
        override fun toString(): String {
            return "null"
        }
    }

    @Serializable
    class JsonStringValue(val value: String) : JsonValue() {

        override fun toString(): String {
            return "\"$value\""
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is JsonStringValue) return false

            if (value != other.value) return false

            return true
        }

        override fun hashCode(): Int {
            return value.hashCode()
        }
    }

    @Serializable
    sealed class JsonContainer : JsonValue() {

        @Serializable
        sealed class JsonObject : JsonContainer(), Iterable<JsonField<*>> {

            abstract val values: Map<String, JsonValue>

            abstract val fields: Collection<JsonField<*>>

            abstract val fieldsNames: Collection<String>


            abstract operator fun get(valueName: String): JsonField<*>?

            @Serializable
            object EmptyJsonObject : JsonObject(), Iterable<JsonField<*>> {

                private val emptyJsonFieldIterator: Iterator<JsonField<*>> = object : Iterator<JsonField<*>> {
                    override fun hasNext(): Boolean = false

                    override fun next(): JsonField<*> = error("EmptyJsonObject does not contains fields.")

                }
                override val values: Map<String, JsonValue> = emptyMap()

                override val fields: Collection<JsonField<*>> = emptyList()

                override val fieldsNames: Collection<String> = emptyList()

                override fun get(valueName: String): JsonField<*>? = null

                override fun toString(): String {
                    return "{}"
                }

                override fun iterator(): Iterator<JsonField<*>> = emptyJsonFieldIterator

            }

            @Serializable
            class NotEmptyJsonObject  constructor(private val _fields: Map<String, JsonValue>) : JsonObject(), Iterable<JsonField<*>> {

                override fun iterator(): Iterator<JsonField<*>> =
                    JsonObjectFieldIterator(_fields.entries.asSequence().iterator())

                override val values: Map<String, JsonValue> = _fields.toMap()

                override val fields: Collection<JsonField<*>> = _fields.fields

                override val fieldsNames: Collection<String> = _fields.keys.toList()

                override operator fun get(valueName: String): JsonField<*>? =
                    _fields[valueName]?.toJsonField(valueName)

                override fun equals(other: Any?): Boolean {
                    if (this === other) return true
                    if (other !is NotEmptyJsonObject) return false

                    if (fields != other.fields) return false

                    return true
                }


                override fun hashCode(): Int {
                    return fields.hashCode()
                }

                override fun toString(): String {
                    return _fields.entries.joinToString(prefix = "{", postfix = "}", separator = ",") { "\"${it.key}\":${it.value}" }
                }


                private class JsonObjectFieldIterator(private val fields: Iterator<Map.Entry<String, JsonValue>>) :
                    Iterator<JsonField<*>> {

                    override fun hasNext(): Boolean = fields.hasNext()

                    override fun next(): JsonField<*> = fields.next().let { it.value toJsonField it.key }
                }

            }

            //region #################### -> Companion <- ####################
            companion object {

                private val Map<String, JsonValue>.fields: Collection<JsonField<*>>
                    get() = entries.map { it.key asJsonField it.value }

                operator fun invoke(): JsonObject = EmptyJsonObject

                operator fun invoke(fields: Map<String, JsonValue>): JsonObject {
                    return if (fields.isEmpty()) EmptyJsonObject
                    else NotEmptyJsonObject(fields)
                }
            }
            //endregion
        }

        @Serializable
        sealed class JsonArray : JsonContainer() {

            @Serializable
            object EmptyJsonArray : JsonArray() {
                override fun toString(): String {
                    return "[]"
                }
            }

            @Serializable
            data class NotEmptyJsonArray(private val items: List<JsonValue>) : JsonArray(), Iterable<JsonValue> by items {

                operator fun get(index: Int): JsonValue? = items[index]

                override fun toString(): String {
                    return items.joinToString(prefix = "[", postfix = "]", separator = ",") { it.toString() }
                }

                override fun equals(other: Any?): Boolean {
                    if (this === other) return true
                    if (other !is NotEmptyJsonArray) return false

                    if (items != other.items) return false

                    return true
                }

                override fun hashCode(): Int {
                    return items.hashCode()
                }
            }

            //region #################### -> Companion <- ####################
            companion object {

                operator fun invoke(): JsonArray = EmptyJsonArray

                operator fun invoke(items: List<JsonValue>): JsonArray {
                    return if (items.isEmpty()) EmptyJsonArray
                    else NotEmptyJsonArray(items)
                }
            }
            //endregion
        }
    }

    @Serializable
    sealed class JsonBooleanValue(val value: Boolean) : JsonValue() {

        @Serializable
        object JsonTrueValue : JsonBooleanValue(true) {
            override fun toString(): String {
                return "true"
            }
        }

        @Serializable
        object JsonFalseValue : JsonBooleanValue(false) {
            override fun toString(): String {
                return "false"
            }
        }

        //region #################### -> Companion <- ####################
        companion object {
            operator fun invoke(value: Boolean): JsonBooleanValue =
                if (value) JsonTrueValue else JsonFalseValue
        }
        //endregion
    }

    @Serializable
    sealed class JsonNumberValue(val value: String) : JsonValue() {

        override fun toString(): String = value

        @Serializable
        class JsonIntegerValue  : JsonNumberValue {

            constructor(value: Int) : super("$value")

            constructor(value: Short): super("$value")

            constructor(value: Byte): super("$value")

            constructor(value: Long):  super("$value")

            constructor(integerString: String): super(integerString){

                require(integerValueRegex.matches(integerString)) { "Integer value must contains only minus sign and digits. Received parameter: $integerString" }

            }

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is JsonIntegerValue) return false
                return value == other.value
            }

            override fun hashCode(): Int {
                return value.hashCode()
            }

        }

        @Serializable
        class JsonRealValue  constructor(val integerValue: String, val decimalValue: String) : JsonNumberValue("$integerValue.$decimalValue") {

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is JsonRealValue) return false

                if (integerValue != other.integerValue) return false
                if (decimalValue != other.decimalValue) return false

                return true
            }

            override fun hashCode(): Int {
                return 31 * integerValue.hashCode() + decimalValue.hashCode()
            }

            //region #################### -> Companion <- ####################
            companion object {
                private val digitRegex = "^\\d+$".toRegex()

                operator fun invoke(value: Double): JsonRealValue {
                    val repr = "$value".split(".")
                    return JsonRealValue(repr[0], repr.getOrElse(1) { "" })
                }

                operator fun invoke(value: Float): JsonRealValue {
                    val repr = "$value".split(".")
                    return JsonRealValue(repr[0], repr.getOrElse(1) { "" })
                }

                operator fun invoke(integerValue: Int, decimalValue: Int): JsonRealValue {
                    return JsonRealValue("$integerValue", "$decimalValue")
                }

                operator fun invoke(integerValue: String, decimalValue: String): JsonRealValue {
                    require(integerValueRegex.matches(integerValue)) { "Integer value must contains only minus sign or digits. Received value: $integerValue" }
                    require(digitRegex.matches(decimalValue)) { "Decimal value must contains only digits. Received value: $decimalValue" }
                    return JsonRealValue(integerValue, decimalValue)
                }

                operator fun invoke(integerValue: Long, decimalValue: Long): JsonRealValue {
                    return JsonRealValue("$integerValue", "$decimalValue")
                }
            }
            //endregion
        }

        @Serializable
        class JsonExponentialValue  constructor(val mantissa: String, val orderOfMagnitude: String) : JsonNumberValue("${mantissa}e$orderOfMagnitude") {

            override fun equals(other: Any?): Boolean {
                if (this === other) return true
                if (other !is JsonExponentialValue) return false

                if (mantissa != other.mantissa) return false
                if (orderOfMagnitude != other.orderOfMagnitude) return false

                return true
            }

            override fun hashCode(): Int {
                return 31 * mantissa.hashCode() + orderOfMagnitude.hashCode()
            }

            //region #################### -> Companion <- ####################
            companion object {

                private val mantissaRegex = "^\\d+?(?:\\.?\\d+?)?\$".toRegex()
                private val orderOfMagnitudeRegex = "^[-+]?\\d+$".toRegex()
                private val fullExponentialRecordRegex = "^(?<mantissa>\\d+?(?:\\.?\\d+?)?)e(?<orderOfMagnitude>[-+]?\\d+)\$".toRegex()

                operator fun invoke(mantissa: String, orderOfMagnitude: String): JsonExponentialValue {
                    require(mantissaRegex.matches(mantissa)) { "Mantissa must contains only digits and one dot. Regex for test: \"${mantissaRegex.pattern}\". Received parameter: \"$mantissa\"" }
                    require(orderOfMagnitudeRegex.matches(orderOfMagnitude)) { "Order of magnitude must contains only sign and digits. Regex for test: \"${orderOfMagnitudeRegex.pattern}\". Received parameter: $orderOfMagnitude" }
                    return JsonExponentialValue(mantissa, orderOfMagnitude)
                }

                operator fun invoke(exponentialRecord: String): JsonExponentialValue {
                    val value = fullExponentialRecordRegex.matchEntire(exponentialRecord)
                    require(value != null) { "Exponential record must be match regex ${fullExponentialRecordRegex.pattern}. Received parameter: \"$exponentialRecord\"" }
                    return JsonExponentialValue(mantissa = value.groupValues[1], orderOfMagnitude = value.groupValues[2])
                }
            }
            //endregion
        }

    }

    //region #################### -> Companion <- ####################
    companion object {
        private val integerValueRegex = "^-?\\d+$".toRegex()

    }
    //endregion
}

@Suppress("Nothing_to_inline")
inline infix fun <T : JsonValue> T.toJsonField(name: String): JsonField<T> =
    JsonField(name = name, value = this)

@Suppress("Nothing_to_inline")
inline infix fun <T : JsonValue> String.asJsonField(value: T): JsonField<T> =
    JsonField(name = this, value = value)
