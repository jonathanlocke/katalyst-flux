package jonathanlocke.katalyst.data.structures.sets

import jonathanlocke.katalyst.data.structures.SafeDataStructure.Companion.safeSet
import jonathanlocke.katalyst.data.values.numeric.count.Count.Companion.count
import jonathanlocke.katalyst.problems.ProblemException
import jonathanlocke.katalyst.problems.ProblemList
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class SafeSetTest {

    @Test
    fun testWarning() {
        val problems = ProblemList()
        val set = safeSet<Int>(warningSize = count(10), problemHandler = problems)
        set.addAll(1..100)
        assert(problems.size == 1)
        assert(problems.isValid())
        assert(problems.warnings() == 1)
        assert(problems.errors() == 0)
    }

    @Test
    fun testError() {
        assertThrows<ProblemException> {
            val set = safeSet<Int>(maximumSize = count(10))
            set.addAll(1..100)
        }
    }
}