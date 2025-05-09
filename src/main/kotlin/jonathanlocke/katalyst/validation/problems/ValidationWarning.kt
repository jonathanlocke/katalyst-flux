package jonathanlocke.katalyst.validation.problems

import jonathanlocke.katalyst.problems.Problem
import jonathanlocke.katalyst.problems.categories.Warning

class ValidationWarning(message: String, cause: Throwable? = null, value: Any? = null) :
    Warning(message, cause, value) {
    override val effect = Effect.CONTINUE
    override fun prefixed(prefix: String): Problem = ValidationWarning(prefix + message, cause, value)
}