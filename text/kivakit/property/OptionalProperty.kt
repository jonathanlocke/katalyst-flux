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
package jonathanlocke.katalyst.cripsr.kivakit.property

import com.telenav.kivakit.annotations.code.quality.TypeQuality

/**
 * This annotation signals that a missing property value should not be considered a problem.
 *
 * @author jonathanl (shibo)
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@UmlClassDiagram(diagram = DiagramReflection::class)
@TypeQuality(stability = STABLE, testing = TESTING_NOT_NEEDED, documentation = DOCUMENTED)
annotation class OptionalProperty


