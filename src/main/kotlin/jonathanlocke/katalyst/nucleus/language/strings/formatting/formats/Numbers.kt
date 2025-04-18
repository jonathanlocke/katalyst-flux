package jonathanlocke.katalyst.nucleus.language.strings.formatting.formats

import jonathanlocke.katalyst.nucleus.language.strings.formatting.StringFormatter

class Numbers {

    companion object {
        val ThousandsSeparated = StringFormatter<Number> { "%,d".format(it.toLong()) }
    }
}