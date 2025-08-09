/*
 * Copyright 2025 meopedevts
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.meopedevts.typedvo.exceptions

class TypedVOConversionException(
    targetType: String,
    message: String = "Falha na conversão de VO para $targetType",
    cause: Throwable? = null
): RuntimeException(message, cause) {
    constructor(
        targetClass: Class<*>,
        cause: Throwable? = null
    ): this(
        targetType = targetClass.simpleName ?: "Unknown",
        message = "Não foi possível converter DynamicVO para ${targetClass.simpleName}",
        cause = cause
    )
}
