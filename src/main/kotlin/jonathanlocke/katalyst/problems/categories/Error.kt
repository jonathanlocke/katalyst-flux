package jonathanlocke.katalyst.problems.categories

import jonathanlocke.katalyst.problems.Problem
import jonathanlocke.katalyst.problems.Problem.Effect.STOP

/**
 * An error.
 *
 * @param message A message describing the error
 * @param cause Any exception that caused the error
 * @param value Any value associated with the error
 *
 * @see Problem
 */
open class Error(message: String, cause: Throwable? = null, value: Any? = null) : Problem(message, cause, value) {
    override val effect = STOP
    override fun prefixed(prefix: String): Problem = Error(prefix + message, cause, value)
}