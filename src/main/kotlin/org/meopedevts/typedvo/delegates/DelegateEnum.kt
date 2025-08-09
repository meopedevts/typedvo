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
package org.meopedevts.typedvo.delegates

import br.com.sankhya.jape.vo.DynamicVO
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class DelegateEnum<T : Enum<T>>(
    private val clazz: Class<T>
): ReadWriteProperty<DynamicVO, T?> {
    override fun getValue(thisRef: DynamicVO, property: KProperty<*>): T? {
        val nameProperty = property.name.uppercase()
        return clazz.enumConstants.find {
            clazz.getMethod("getValue").invoke(it) == (thisRef.asString(nameProperty) ?: "")
        }
    }

    override fun setValue(thisRef: DynamicVO, property: KProperty<*>, value: T?) {
        val nameProperty = property.name.uppercase()
        if (value == null) {
            thisRef.setProperty(nameProperty, null)
            return
        }
        thisRef.setProperty(nameProperty, clazz.getMethod("getValue").invoke(value))
    }
}
