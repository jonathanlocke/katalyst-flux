package jonathanlocke.katalyst.reflection.kotlin

import jonathanlocke.katalyst.reflection.ValueType
import jonathanlocke.katalyst.reflection.properties.Property
import java.util.*
import kotlin.reflect.KClass
import kotlin.reflect.KProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.memberProperties

class KotlinValueType<Value : Any>(
    override val valueClass: KClass<Value>,
    override val parameterClass: KClass<*>?
) : ValueType<Value> {

    @Suppress("UNCHECKED_CAST")
    override fun memberProperties(): List<Property<*>> =
        valueClass.memberProperties.map { Property.Companion.property(it as KProperty<Any>) }

    override fun property(name: String): Property<*>? = memberProperties().find { it.name == name }
    override fun supertypes(): List<KotlinValueType<*>> =
        valueClass.supertypes.mapNotNull { it.classifier as? KClass<*> }.map { KotlinValueType(it, null) }

    override fun createInstance(): Value = valueClass.createInstance()
    override val simpleName: String = valueClass.simpleName!!
    override val qualifiedName: String = valueClass.qualifiedName!!
    override val packageName: String = valueClass.java.`package`.name

    override fun superProperties(): List<Property<*>> = supertypes().flatMap { it.memberProperties() }
    override fun declaredProperties(): List<Property<*>> =
        memberProperties().filter { property: Property<*> -> property !in superProperties() }.toList()

    override fun equals(other: Any?) =
        other is KotlinValueType<*> && valueClass == other.valueClass && parameterClass == other.parameterClass

    override fun hashCode(): Int = Objects.hash(valueClass, parameterClass)
    override fun toString(): String = "${valueClass.simpleName}${parameterClass?.let { "<$it>" } ?: ""}"
}