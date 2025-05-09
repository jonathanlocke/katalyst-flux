package jonathanlocke.katalyst.problems.handlers

import jonathanlocke.katalyst.problems.Problem
import jonathanlocke.katalyst.problems.ProblemHandler
import jonathanlocke.katalyst.problems.ProblemHandlerBase

class PrefixingProblemHandler(
    private val prefix: String, private val problemHandler: ProblemHandler
) : ProblemHandlerBase() {
    override fun onReceive(problem: Problem) {
        problemHandler.receive(problem.prefixed(prefix))
    }
}