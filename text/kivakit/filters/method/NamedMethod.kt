/** ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// */ //
// © 2011-2021 Telenav, Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
//
/**///////////////////////////////////////////////////////////////////////////////////////////////////////////////////// */
package jonathanlocke.katalyst.cripsr.kivakit.filters.method

import com.telenav.kivakit.annotations.code.quality.TypeQuality

/**
 * This filter matches a field with a particular name
 */
@UmlClassDiagram(diagram = DiagramReflection::class)
@TypeQuality(stability = STABLE, testing = UNTESTED, documentation = DOCUMENTED)
class NamedMethod(
    convention: PropertyNamingConvention?,
    /** The method name  */
    private val name: String
) : AllMethods(convention) {
    /**
     * {@inheritDoc}
     */
    override fun equals(`object`: Any): Boolean {
        if (`object` is NamedMethod) {
            return name == `object`.name
        }
        return false
    }

    /**
     * {@inheritDoc}
     */
    override fun hashCode(): Int {
        return hashMany(name)
    }

    /**
     * {@inheritDoc}
     */
    override fun includeAsGetter(method: Method): Boolean {
        // Include getter if the name matches
        return super.includeAsGetter(method) && method.name().equals(name)
    }
}
