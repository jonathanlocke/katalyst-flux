package jonathanlocke.katalyst.nucleus.language.errors

import jonathanlocke.katalyst.nucleus.language.errors.handlers.ReturnDefault
import jonathanlocke.katalyst.nucleus.language.errors.handlers.ReturnNull
import jonathanlocke.katalyst.nucleus.language.errors.handlers.Throw
import jonathanlocke.katalyst.nucleus.values.Bytes

/**
 * An [ErrorHandler] allows code to be flexible in how it handles error conditions in different usage contexts.
 *
 * In one context, it might be desirable for a method to throw an exception, while in another context, it might
 * be desirable for the same method to return a null value for performance reasons.
 *
 * # Integer.parseInt()
 *
 * The classic example of this problem is Java's [Integer.parseInt] method, which throws a [NumberFormatException] if
 * its argument is not a valid integer. While this may be convenient in many cases, in some use contexts (like
 * parsing large amounts of dirty data) throwing an exception may have unacceptable performance.
 *
 * In the specific case of [String.toInt] (which is bound by Kotlin to Integer.parseInt), Kotlin has provided a
 * workaround with [String.toIntOrNull]. But this leaves something to be desired because the functionality of integer
 * parsing had to be duplicated.
 *
 * [ErrorHandler] provides a way to avoid this kind of duplication by allowing the caller of a method to
 * specify how the error handling should work.
 *
 * For example, the integer parsing problem could be solved using [ErrorHandler] like this:
 *
 * ```
 * fun String.parseInt(onError: ErrorHandlingStrategy<Int> = Throw()): Int? { ... }
 * ```
 *
 * With this hypothetical implementation of [String.parseInt], when the caller wants an exception thrown they can
 * just invoke the method and the default strategy will be to [Throw]:
 *
 * ```
 * "5.6".parseInt()
 * ```
 *
 * but when there is a need to avoid throwing exceptions, the caller could do this:
 *
 * ```
 * dirtyText.parseInt(ReturnNull())
 * ```
 *
 * Note that because the return value of the extension method here is Int?, it can be helpful to provide an overload
 * that invokes the version that throws an exception and returns a non-nullable Int value:
 *
 * ```
 * fun String.parseInt(): Int = parseInt()
 * ```
 *
 * Kotlin's compiler is smart enough to figure out that the return value cannot be null!
 *
 * #  Bytes
 *
 * For another example of how [ErrorHandler] can be used effectively, see [Bytes]
 *
 * @see ReturnNull
 * @see ReturnDefault
 * @see Throw
 * @see Bytes
 */
interface ErrorHandler<T> {

    fun error(message: String, throwable: Throwable? = null, value: T? = null): T?
}
