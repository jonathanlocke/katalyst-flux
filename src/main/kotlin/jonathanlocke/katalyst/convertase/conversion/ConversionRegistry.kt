package jonathanlocke.katalyst.convertase.conversion

import jonathanlocke.katalyst.convertase.conversion.ConversionRegistry.Companion.conversionRegistry
import jonathanlocke.katalyst.convertase.conversion.ConversionRegistry.Companion.registerConversions
import jonathanlocke.katalyst.nucleus.language.collections.maps.MultiMap
import kotlin.reflect.KClass
import kotlin.reflect.KProperty1
import kotlin.reflect.full.companionObject
import kotlin.reflect.full.companionObjectInstance

/**
 * Registry for [Conversion]s.
 *
 *  - [conversionRegistry] - The singleton registry instance
 *  - [register] - Registers a conversion with the registry
 *  - [registerConversions] - Registers all [Conversion] properties of the given class' companion object
 *                            by forcing each conversion property to be created. When the conversion is
 *                            created,
 *
 *  **Example**
 *
 *  ```
 *      init {
 *         registerConversions(this::class)
 *     }
 *
 *     companion object {
 *
 *         val ToByte = stringToValueConverter(Byte::class) { text, errorHandler ->
 *             text.toByteOrNull() ?: errorHandler.error("Invalid Byte value $text")
 *         }
 *
 *         val ToInt = stringToValueConverter(Int::class) { text, errorHandler ->
 *             text.toIntOrNull() ?: errorHandler.error("Invalid Int value $text")
 *         }
 *     }
 * ```
 *
 * @see Conversion
 * @see ConversionBase
 */
class ConversionRegistry {

    /** The registry of conversions keyed by type */
    private val conversions = MultiMap<KClass<*>, Conversion<*, *>>()

    /**
     * Registers a conversion with the registry
     * @param from The source type of the conversion
     * @param to The target type of the conversion
     * @param conversion The conversion to register
     */
    fun register(from: KClass<*>, to: KClass<*>, conversion: Conversion<*, *>) {
        synchronized(conversions) {
            conversions.put(from, conversion)
            conversions.put(to, conversion)
        }
    }

    companion object {

        /** The singleton registry instance */
        @JvmStatic
        val conversionRegistry = ConversionRegistry()

        /**
         * Registers all [Conversion] properties of the given class' companion object
         */
        fun registerConversions(type: KClass<*>) {

            // If there is a companion object,
            type.companionObject?.let { companion ->

                // and a companion object instance,
                type.companionObjectInstance?.let { instance ->

                    // for each member of the companion object,
                    companion.members.forEach {

                        // that is a property,
                        if (it is KProperty1<*, *>) {

                            // if it is a Conversion property,
                            if (it.returnType.classifier == Conversion::class) {

                                // register it by getting the value of the property.
                                @Suppress("UNCHECKED_CAST")
                                (it as KProperty1<Any, *>).get(instance)
                            }
                        }
                    }
                }
            }
        }
    }
}
